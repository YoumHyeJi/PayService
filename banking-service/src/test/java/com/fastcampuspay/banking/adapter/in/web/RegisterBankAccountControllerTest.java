package com.fastcampuspay.banking.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RegisterBankAccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;


    @Test
    public void testRegisterBankAccount() throws Exception{
        RegisterBankAccountRequest request = new RegisterBankAccountRequest(1L, "국민은행", "123456", true);

//        BankAccount expect = BankAccount(
//                new Membership.MembershipId(1L),
//                new Membership.MembershipName("name"),
//                new Membership.MembershipEmail("email"),
//                new Membership.MembershipAddress("address"),
//                new Membership.MembershipIsValid(true),
//                new Membership.MembershipIsCorp(false)
//        );

        mockMvc.perform(
                        MockMvcRequestBuilders.post("/banking/account/register")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(mapper.writeValueAsString(request))
                )
                .andExpect(MockMvcResultMatchers.status().isOk());
//                .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(expect)));
    }


}