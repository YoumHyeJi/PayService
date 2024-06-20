package org.fastcampuspay.membership.application.port.in;

import org.fastcampuspay.membership.domain.Membership;

public interface ModifyMembershipUseCase {

    Membership modifyMembership(ModifyMembershipCommand command);

}
