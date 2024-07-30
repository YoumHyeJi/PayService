package com.fastcampuspay.remittance.application.service;

import com.fastcampuspay.remittance.adapter.in.web.CancelRemittanceResponse;
import com.fastcampuspay.remittance.adapter.out.persistence.RemittanceRequestJpaEntity;
import com.fastcampuspay.remittance.application.port.in.CancelRemittanceCommand;
import com.fastcampuspay.remittance.application.port.in.CancelRemittanceUseCase;
import com.fastcampuspay.remittance.application.port.out.FindRemittancePort;
import com.fastcampuspay.remittance.application.port.out.RequestRemittancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CancelRemittanceService implements CancelRemittanceUseCase {

    private final FindRemittancePort findRemittancePort;
    private final RequestRemittancePort requestRemittancePort;

    @Override
    public CancelRemittanceResponse cancelRemittance(CancelRemittanceCommand command) {

        RemittanceRequestJpaEntity remittanceRequestJpaEntity = findRemittancePort.findRemittanceOne(command);
        remittanceRequestJpaEntity.setRemittanceStatus("CANCELLED");
        requestRemittancePort.saveRemittanceRequestHistory(remittanceRequestJpaEntity);
        return CancelRemittanceResponse.builder()
            .isSuccess(true)
            .build();
    }
}
