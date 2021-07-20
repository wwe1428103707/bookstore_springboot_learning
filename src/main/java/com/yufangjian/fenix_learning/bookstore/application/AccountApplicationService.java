package com.yufangjian.fenix_learning.bookstore.application;

import com.yufangjian.fenix_learning.bookstore.domain.account.Account;
import com.yufangjian.fenix_learning.bookstore.domain.account.AccountRepository;
import com.yufangjian.fenix_learning.bookstore.infrastructure.utility.Encryption;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@Transactional
public class AccountApplicationService {

    @Inject
    private AccountRepository repository;

    @Inject
    private Encryption encoder;

    public void createAccount(Account account){
        account.setPassword(encoder.encode(account.getPassword()));
        repository.save(account);
    }

    public Account findAccountByUserName(String name){
        return repository.findByUsername(name);
    }

    public void updateAccount(Account account){
        repository.save(account);
    }
}
