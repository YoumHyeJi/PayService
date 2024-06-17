package org.fastcampuspay.membership.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.fastcampuspay.membership.application.port.in.RegisterMembershipCommand;
import org.fastcampuspay.membership.application.port.in.RegisterMembershipUseCase;
import org.fastcampuspay.membership.common.WebAdapter;
import org.fastcampuspay.membership.domain.Membership;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class RegisterMembershipController {

    private final RegisterMembershipUseCase registerMembershipUseCase;

    @PostMapping(path = "/membership/register")
    Membership registerMembership(@RequestBody RegisterMembershipRequest request){
        // request

        // request -> command

        // useCase
        RegisterMembershipCommand command = RegisterMembershipCommand.builder()
                .name(request.getName())
                .address(request.getAddress())
                .email(request.getEmail())
                .isValid(true)
                .isCorp(request.isCorp())
                .build();

        return registerMembershipUseCase.registerMembership(command);
    }
}
