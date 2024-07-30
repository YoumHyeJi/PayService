package com.fastcampuspay.remittance.adapter.in.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelRemittanceResponse {

    private Boolean isSuccess;
}
