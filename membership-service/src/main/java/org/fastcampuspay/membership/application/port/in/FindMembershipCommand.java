package org.fastcampuspay.membership.application.port.in;

import lombok.*;
import org.fastcampuspay.membership.common.SelfValidating;

import javax.validation.constraints.NotNull;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class FindMembershipCommand extends SelfValidating<FindMembershipCommand> {

    @NotNull
    private Long membershipId;

    public FindMembershipCommand(Long membershipId) {
        this.membershipId = membershipId;
        this.validateSelf();
    }
}
