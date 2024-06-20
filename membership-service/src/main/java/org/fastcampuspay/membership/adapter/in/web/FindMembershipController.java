package org.fastcampuspay.membership.adapter.in.web;

import com.fastcampuspay.common.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.fastcampuspay.membership.application.port.in.FindMembershipCommand;
import org.fastcampuspay.membership.application.port.in.FindMembershipUseCase;
import org.fastcampuspay.membership.domain.Membership;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@WebAdapter
@RestController
@RequiredArgsConstructor
public class FindMembershipController {

    private final FindMembershipUseCase findMembershipUseCase;

    @GetMapping(path = "/membership/{membershipId}")
    ResponseEntity<Membership> findMembershipByMemberId(@PathVariable("membershipId") Long membershipId) {
        // path variable -> command
        FindMembershipCommand command = FindMembershipCommand.builder()
                .membershipId(membershipId)
                .build();

        return ResponseEntity.ok(findMembershipUseCase.findMembership(command));

    }

    @GetMapping(path = "/hello")
    String hello() {
        return "hello";
    }


}

