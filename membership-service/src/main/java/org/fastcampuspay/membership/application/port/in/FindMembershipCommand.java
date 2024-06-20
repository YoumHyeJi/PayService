package org.fastcampuspay.membership.application.port.in;

import com.fastcampuspay.common.SelfValidating;
import lombok.*;

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
