package com.fastcampuspay.money.application.service;

import com.fastcampuspay.common.CountDownLatchManager;
import com.fastcampuspay.common.RechargingMoneyTask;
import com.fastcampuspay.common.SubTask;
import com.fastcampuspay.common.UseCase;
import com.fastcampuspay.money.adapter.out.persistence.MemberMoneyJpaEntity;
import com.fastcampuspay.money.adapter.out.persistence.MoneyChangingRequestMapper;
import com.fastcampuspay.money.application.port.in.IncreaseMoneyRequestCommand;
import com.fastcampuspay.money.application.port.in.IncreaseMoneyRequestUseCase;
import com.fastcampuspay.money.application.port.out.GetMembershipPort;
import com.fastcampuspay.money.application.port.out.IncreaseMoneyPort;
import com.fastcampuspay.money.application.port.out.SendRechargingMoneyTaskPort;
import com.fastcampuspay.money.domain.MemberMoney;
import com.fastcampuspay.money.domain.MoneyChangingRequest;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.UUID;
import org.springframework.scheduling.config.Task;

@UseCase
@RequiredArgsConstructor
@Transactional
public class IncreaseMoneyRequestService implements IncreaseMoneyRequestUseCase {

    private final CountDownLatchManager countDownLatchManager;
    private final SendRechargingMoneyTaskPort sendRechargingMoneyTaskPort;
    private final GetMembershipPort membershipPort;
    private final IncreaseMoneyPort increaseMoneyPort;
    private final MoneyChangingRequestMapper mapper;

    @Override
    public MoneyChangingRequest increaseMoneyRequest(IncreaseMoneyRequestCommand command) {

        // 머니의 충전.증액이라는 과정
        // 1. 고객 정보가 정상인지 확인 (멤버)
        membershipPort.getMembership(Long.valueOf(command.getTargetMembershipId()));

        // 2. 고객의 연동된 계좌가 있는지, 고객의 연동된 계좌의 잔액이 충분한지도 확인 (뱅킹)

        // 3. 법인 계좌 상태도 정상인지 확인 (뱅킹)

        // 4. 증액을 위한 "기록". 요청 상태로 MoneyChangingRequest 를 생성한다. (MoneyChangingRequest)

        // 5. 펌뱅킹을 수행하고 (고객의 연동된 계좌 -> 패캠페이 법인 계좌) (뱅킹)

        // 6-1. 결과가 정상적이라면. 성공으로 MoneyChangingRequest 상태값을 변동 후에 리턴
        // 성공 시에 멤버의 MemberMoney 값 증액이 필요해요
        MemberMoneyJpaEntity memberMoneyJpaEntity = increaseMoneyPort.increaseMoney(
                new MemberMoney.MembershipId(command.getTargetMembershipId())
                ,command.getAmount());

        if(memberMoneyJpaEntity != null) {
            return mapper.mapToDomainEntity(increaseMoneyPort.createMoneyChangingRequest(
                            new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                            new MoneyChangingRequest.MoneyChangingType(1),
                            new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                            new MoneyChangingRequest.MoneyChangingStatus(1),
                            new MoneyChangingRequest.Uuid(UUID.randomUUID().toString())
                    )
            );
        }

        // 6-2. 결과가 실패라면, 실패라고 MoneyChangingRequest 상태값을 변동 후에 리턴
        return null;
    }

    @Override
    public MoneyChangingRequest increaseMoneyRequestAsync(IncreaseMoneyRequestCommand command) {

        // subtask = 각 서비스에 특정 membershipId로 validation 을 하기위한 task
        // 1. SubTask, Task 생성

        // 1-1. membership subtask 생성
        SubTask validMemberTask = SubTask.builder()
            .subTaskName("validMemberTask : " + "멤버십 유효성 검사")
            .membershipID(command.getTargetMembershipId())
            .taskType("membership")
            .status("ready")
            .build();

        // 1-2. banking sub task 생성
        SubTask validBankingAccountTask = SubTask.builder()
            .subTaskName("validBankingAccountTask : " + "뱅킹 계좌 유효성 검사")
            .membershipID(command.getTargetMembershipId())
            .taskType("banking")
            .status("ready")
            .build();

        // 1-3. amount money firmbanking subtask -> 생략 (무조건 오케이 받았다고 가정)


        // 1-4. task 생성
        List<SubTask> subTaskList = new ArrayList<>();
        subTaskList.add(validMemberTask);
        subTaskList.add(validBankingAccountTask);

        RechargingMoneyTask task = RechargingMoneyTask.builder()
            .taskID(UUID.randomUUID().toString())
            .taskName("Increase Money Task / 머니 충전 Task")
            .subTaskList(subTaskList)
            .moneyAmount(command.getAmount())
            .membershipID(command.getTargetMembershipId())
            .toBankName("fastcampus")
            .toBankAccountNumber("123-456")
            .build();


        // 2. kafka cluster produce
        sendRechargingMoneyTaskPort.sendRechargingMoneyTaskPort(task);
        countDownLatchManager.addCountDownLatch(task.getTaskID());

        // 3. wait
        try {
            countDownLatchManager.getCountDownLatch(task.getTaskID()).await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // 3-1. task-consumer
        // 등록된 sub-task, status 모두 ok -> task 결과를 produce

        // 4. Task Result Consume
        // 받은 응답을 다시 countDownLatchManager를 통해 결과를 받는다.
        String result = countDownLatchManager.getDataForKey(task.getTaskID());

        if (result.equals("success")){
            // 6-1. 결과가 정상적이라면. 성공으로 MoneyChangingRequest 상태값을 변동 후에 리턴
            // 성공 시에 멤버의 MemberMoney 값 증액이 필요해요
            MemberMoneyJpaEntity memberMoneyJpaEntity = increaseMoneyPort.increaseMoney(
                new MemberMoney.MembershipId(command.getTargetMembershipId())
                ,command.getAmount());

            if(memberMoneyJpaEntity != null) {
                return mapper.mapToDomainEntity(increaseMoneyPort.createMoneyChangingRequest(
                        new MoneyChangingRequest.TargetMembershipId(command.getTargetMembershipId()),
                        new MoneyChangingRequest.MoneyChangingType(1),
                        new MoneyChangingRequest.ChangingMoneyAmount(command.getAmount()),
                        new MoneyChangingRequest.MoneyChangingStatus(1),
                        new MoneyChangingRequest.Uuid(UUID.randomUUID().toString())
                    )
                );
            }

        }
        else{
            // 5-2. Consume fail -> Logic
            return null;
        }

        // 5. Consume ok -> Logic
        return null;
    }
}
