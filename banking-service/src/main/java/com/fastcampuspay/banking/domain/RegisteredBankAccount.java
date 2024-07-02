package com.fastcampuspay.banking.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class RegisteredBankAccount {

    // Membership : 오염이 되면 안 되는 클래스. 고객 정보. 핵심 도메인

    private final Long registeredBankAccountId;

    private final Long membershipId;

    private final String bankName;  // enum

    private final String bankAccountNumber;

    private final boolean linkedStatusIsValid;


    public static RegisteredBankAccount generateRegisteredBankAccount(
            RegisteredBankAccountId registeredBankAccountId,
            MembershipId membershipId,
            BankName bankName,
            BankAccountNumber bankAccountNumber,
            LinkedStatusIsValid linkedStatusIsValid
         ) {
        return new RegisteredBankAccount(
                registeredBankAccountId.registeredBankAccountId,
                membershipId.membershipId,
                bankName.bankName,
                bankAccountNumber.bankAccountNumber,
                linkedStatusIsValid.linkedStatusIsValid);
    }

    @Value
    public static class RegisteredBankAccountId {

        Long registeredBankAccountId;

        public RegisteredBankAccountId(Long value) {
            this.registeredBankAccountId = value;
        }
    }

    @Value
    public static class MembershipId {

        Long membershipId;

        public MembershipId(Long value) {
            this.membershipId = value;
        }
    }

    @Value
    public static class BankName {

        String bankName;

        public BankName(String value) {
            this.bankName = value;
        }
    }

    @Value
    public static class BankAccountNumber {

        String bankAccountNumber;

        public BankAccountNumber(String value) {
            this.bankAccountNumber = value;
        }
    }

    @Value
    public static class LinkedStatusIsValid {

        boolean linkedStatusIsValid;

        public LinkedStatusIsValid(boolean value) {
            this.linkedStatusIsValid = value;
        }
    }
}
