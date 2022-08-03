package com.schoolfeespayment.payment.controller;

import com.schoolfeespayment.payment.dto.AccountStatementRequest;
import com.schoolfeespayment.payment.dto.TransferBalanceRequest;
import com.schoolfeespayment.payment.entity.Account;
import com.schoolfeespayment.payment.response.Response;
import com.schoolfeespayment.payment.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    @Autowired
    private AccountService accountService;
    @RequestMapping("/create")
    public List<Account> create(@RequestBody Account account) {
        accountService.save(account);
        return accountService.findAll();
    }

    @RequestMapping("/all")
    public List<Account> all() {
        return accountService.findAll();
    }

    @RequestMapping("/sendmoney")
    public Response sendMoney(
            @RequestBody TransferBalanceRequest transferBalanceRequest
    ) {

        return Response.ok().setPayload(
                accountService.sendMoney(
                        transferBalanceRequest
                )
        );
    }
    @RequestMapping("/statement")
    public Response getStatement(
            @RequestBody AccountStatementRequest accountStatementRequest

    ){
        return Response.ok().setPayload(
                accountService.getStatement(accountStatementRequest.getAccountNumber())
        );

    }

}