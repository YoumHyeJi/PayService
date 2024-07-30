package com.fastcampuspay.remittance.application.port.in;

import com.fastcampuspay.common.SelfValidating;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class CancelRemittanceCommand extends SelfValidating<CancelRemittanceCommand> {

    @NotNull
    private Long remittanceRequestId;

    public CancelRemittanceCommand(Long remittanceRequestId) {
        this.remittanceRequestId = remittanceRequestId;
        this.validateSelf();
    }
}
