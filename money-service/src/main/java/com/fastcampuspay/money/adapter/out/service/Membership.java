package com.fastcampuspay.money.adapter.out.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Membership { // for banking-service only

    private Long membershipId;

    private String name;

    private String email;

    private String address;

    private boolean isValid;

    private boolean isCorp;

}
