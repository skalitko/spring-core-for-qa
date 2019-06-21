package com.acme.banking.dbo.ooad.service;

import com.acme.banking.dbo.ooad.dal.AccountRepository;
import com.acme.banking.dbo.ooad.domain.Account;

public class TransferService {

    private AccountRepository accountRepository;


    public TransferService(AccountRepository ar) {
        this.accountRepository = ar;
    }

    public void transfer(long fromId, long toId, double amount) {
        Account fromAcc = accountRepository.findById(fromId);
        Account toAcc = accountRepository.findById(toId);
        fromAcc.withdraw(amount);
        toAcc.deposit(amount);
        accountRepository.save(fromAcc);
        accountRepository.save(toAcc);
    }
}
