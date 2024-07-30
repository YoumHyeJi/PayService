package com.fastcampuspay.remittance.application.port.in;

import com.fastcampuspay.remittance.adapter.in.web.CancelRemittanceResponse;

public interface CancelRemittanceUseCase {

    CancelRemittanceResponse cancelRemittance(CancelRemittanceCommand command);
}
