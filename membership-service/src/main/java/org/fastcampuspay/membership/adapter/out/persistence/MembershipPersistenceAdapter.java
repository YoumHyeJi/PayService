package org.fastcampuspay.membership.adapter.out.persistence;

import com.fastcampuspay.common.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.fastcampuspay.membership.application.port.out.FindMembershipPort;
import org.fastcampuspay.membership.application.port.out.ModifyMembershipPort;
import org.fastcampuspay.membership.application.port.out.RegisterMembershipPort;
import org.fastcampuspay.membership.domain.Membership;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements RegisterMembershipPort, FindMembershipPort, ModifyMembershipPort {

    private final SpringDataMembershipRepository membershipRepository;

    @Override
    public MembershipJpaEntity createMembership(Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress, Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp) {
        return membershipRepository.save(
                new MembershipJpaEntity(
                        membershipName.getNameValue(),
                        membershipEmail.getEmailValue(),
                        membershipAddress.getAddressValue(),
                        membershipIsValid.isValidValue(),
                        membershipIsCorp.isCorpValue()));
    }

    @Override
    public Optional<MembershipJpaEntity> findMembership(Membership.MembershipId membershipId) {
        return membershipRepository.findById(membershipId.getMembershipId());
    }

    @Override
    public MembershipJpaEntity modifyMembership(Membership.MembershipId membershipId, Membership.MembershipName membershipName, Membership.MembershipEmail membershipEmail, Membership.MembershipAddress membershipAddress, Membership.MembershipIsValid membershipIsValid, Membership.MembershipIsCorp membershipIsCorp) {
        MembershipJpaEntity entity = membershipRepository.getById(membershipId.getMembershipId());

        entity.setName(membershipName.getNameValue());
        entity.setEmail(membershipEmail.getEmailValue());
        entity.setAddress(membershipAddress.getAddressValue());
        entity.setCorp(membershipIsCorp.isCorpValue());
        entity.setValid(membershipIsValid.isValidValue());

        return membershipRepository.save(entity);
    }
}
