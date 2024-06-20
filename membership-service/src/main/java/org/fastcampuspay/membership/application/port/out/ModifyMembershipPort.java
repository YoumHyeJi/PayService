package org.fastcampuspay.membership.application.port.out;

import org.fastcampuspay.membership.adapter.out.persistence.MembershipJpaEntity;
import org.fastcampuspay.membership.domain.Membership;


public interface ModifyMembershipPort {

    MembershipJpaEntity modifyMembership(
            Membership.MembershipId membershipId,
            Membership.MembershipName membershipName,
            Membership.MembershipEmail membershipEmail,
            Membership.MembershipAddress membershipAddress,
            Membership.MembershipIsValid membershipIsValid,
            Membership.MembershipIsCorp membershipIsCorp);
}
