package com.schoolfeespayment.payment.service;


import com.schoolfeespayment.payment.dto.AccountStatement;
import com.schoolfeespayment.payment.dto.TransferBalanceRequest;
import com.schoolfeespayment.payment.entity.Account;
import com.schoolfeespayment.payment.entity.Transaction;

import java.util.List;

public interface AccountService {
    List<Account> findAll();
    Account save(Account account);
    Transaction sendMoney(
            TransferBalanceRequest transferBalanceRequest
    );
    AccountStatement getStatement(String accountNumber);
}