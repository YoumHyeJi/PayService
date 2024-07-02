package com.fastcampuspay.banking.adapter.in.web;

import com.fastcampuspay.banking.application.port.in.RegisterBankAccountCommand;
import com.fastcampuspay.banking.application.port.in.RegisterBankAccountUseCase;
import com.fastcampuspay.banking.domain.RegisteredBankAccount;
import com.fastcampuspay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterBankAccountController {

    private final RegisterBankAccountUseCase registerBankAccountUseCase;

    @PostMapping(path = "/banking/account/register")
    RegisteredBankAccount registerBankAccount(@RequestBody RegisterBankAccountRequest request){
        // request

        // request -> command

        // useCase
        RegisterBankAccountCommand command = RegisterBankAccountCommand.builder()
                .membershipId(request.getMembershipId())
                .bankName(request.getBankName())
                .bankAccountNumber(request.getBankAccountNumber())
                .linkedStatusIsValid(true)
                .build();

        RegisteredBankAccount registeredBankAccount = registerBankAccountUseCase.registerRegisterBankAccount(command);

        if (registeredBankAccount == null){
            // TODO :  Error Handling
            return null;
        }

        return registeredBankAccount;
    }
}
