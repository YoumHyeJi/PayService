package org.fastcampuspay.membership.application.service;

import com.fastcampuspay.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.fastcampuspay.membership.adapter.out.persistence.MembershipJpaEntity;
import org.fastcampuspay.membership.adapter.out.persistence.MembershipMapper;
import org.fastcampuspay.membership.application.port.in.ModifyMembershipCommand;
import org.fastcampuspay.membership.application.port.in.ModifyMembershipUseCase;
import org.fastcampuspay.membership.application.port.out.ModifyMembershipPort;
import org.fastcampuspay.membership.domain.Membership;

import javax.transaction.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class ModifyMembershipService implements ModifyMembershipUseCase {

    private final ModifyMembershipPort modifyMembershipPort;

    private final MembershipMapper membershipMapper;

    @Override
    public Membership modifyMembership(ModifyMembershipCommand command) {

        // command -> DB
        // port -> adapter
        MembershipJpaEntity jpaEntity = modifyMembershipPort.modifyMembership(
                new Membership.MembershipId(command.getMembershipId()),
                new Membership.MembershipName(command.getName()),
                new Membership.MembershipEmail(command.getEmail()),
                new Membership.MembershipAddress(command.getAddress()),
                new Membership.MembershipIsValid(command.isValid()),
                new Membership.MembershipIsCorp(command.isCorp())
        );

        // entity -> Membership
        return membershipMapper.mapToDomainEntity(jpaEntity);
    }
}
