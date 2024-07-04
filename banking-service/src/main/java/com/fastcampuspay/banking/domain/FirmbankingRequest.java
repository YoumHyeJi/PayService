package com.fastcampuspay.banking.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class FirmbankingRequest {

    private final Long firmbankingRequestId;

    private final String fromBankName;

    private final String fromBankAccountNumber;

    private final String toBankName;

    private final String toBankAccountNumber;

    private final int moneyAmount;

    private final int firmbankingStatus; // 0: 요청 1: 완료 2: 실패

    private final UUID uuid;

    public static FirmbankingRequest generateFirmbankingRequest(
            FirmbankingRequestId firmbankingRequestId,
            FromBankName fromBankName,
            FromBankAccountNumber fromBankAccountNumber,
            ToBankName toBankName,
            ToBankAccountNumber toBankAccountNumber,
            MoneyAmount moneyAmount,
            FirmbankingStatus firmbankingStatus,
            UUID uuid
    ) {
        return new FirmbankingRequest(
                firmbankingRequestId.getFirmbankingRequestId(),
                fromBankName.getFromBankName(),
                fromBankAccountNumber.getFromBankAccountNumber(),
                toBankName.getToBankName(),
                toBankAccountNumber.getToBankAccountNumber(),
                moneyAmount.getMoneyAmount(),
                firmbankingStatus.getFirmbankingStatus(),
                uuid);
    }

    @Value
    public static class FirmbankingRequestId {

        Long firmbankingRequestId;

        public FirmbankingRequestId(Long value) {
            this.firmbankingRequestId = value;
        }
    }

    @Value
    public static class FromBankName {

        String fromBankName;

        public FromBankName(String value) {
            this.fromBankName = value;
        }
    }

    @Value
    public static class FromBankAccountNumber {

        String fromBankAccountNumber;

        public FromBankAccountNumber(String value) {
            this.fromBankAccountNumber = value;
        }
    }

    @Value
    public static class ToBankName {

        String toBankName;

        public ToBankName(String value) {
            this.toBankName = value;
        }
    }

    @Value
    public static class ToBankAccountNumber {

        String toBankAccountNumber;

        public ToBankAccountNumber(String value) {
            this.toBankAccountNumber = value;
        }
    }

    @Value
    public static class MoneyAmount {

        int moneyAmount;

        public MoneyAmount(int value) {
            this.moneyAmount = value;
        }
    }

    @Value
    public static class FirmbankingStatus {

        int firmbankingStatus;

        public FirmbankingStatus(int value) {
            this.firmbankingStatus = value;
        }
    }
}
