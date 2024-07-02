package com.fastcampuspay.banking.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBankAccountRequest {

    private Long membershipId;

    private String bankName;

    private String bankAccountNumber;

    private boolean linkedStatusIsValid;
}
