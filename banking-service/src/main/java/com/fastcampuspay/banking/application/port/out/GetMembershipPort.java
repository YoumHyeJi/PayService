package com.fastcampuspay.banking.application.port.out;

import com.fastcampuspay.banking.adapter.out.service.Membership;


public interface GetMembershipPort {

    MembershipStatus getMembership(Long membershipId);
}
