package com.fastcampuspay.banking.adapter.out.external.bank;

import com.fastcampuspay.banking.application.port.out.RequestBankAccountInfoPort;
import com.fastcampuspay.common.ExternalSystemAdapter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ExternalSystemAdapter
public class BankAccountAdapter implements RequestBankAccountInfoPort {

    @Override
    public BankAccount getBankAccountInfo(GetBankAccountRequest request) {

        // 실제로 외부 은행에 http 요청을 통해 유효한 계좌 정보인지 조회해온다.
        return new BankAccount(request.getBankName(), request.getBankAccountNumber(), true);
    }
}
