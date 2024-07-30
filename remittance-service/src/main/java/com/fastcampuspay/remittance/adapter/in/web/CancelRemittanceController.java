package com.fastcampuspay.remittance.adapter.in.web;

import com.fastcampuspay.common.WebAdapter;
import com.fastcampuspay.remittance.application.port.in.CancelRemittanceCommand;
import com.fastcampuspay.remittance.application.port.in.CancelRemittanceUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@WebAdapter
public class CancelRemittanceController {

    private final CancelRemittanceUseCase cancelRemittanceUseCase;

    @PostMapping(path = "")
    public CancelRemittanceResponse cancelRemittance(CancelRemittanceRequest requestDto){

        CancelRemittanceCommand command = CancelRemittanceCommand.builder()
            .remittanceRequestId(requestDto.getRemittanceRequestId())
            .build();

        return cancelRemittanceUseCase.cancelRemittance(command);
    }
}
