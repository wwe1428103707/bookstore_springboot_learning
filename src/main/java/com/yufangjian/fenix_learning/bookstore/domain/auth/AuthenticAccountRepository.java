package com.yufangjian.fenix_learning.bookstore.domain.auth;

import com.yufangjian.fenix_learning.bookstore.domain.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticAccountRepository {
    @Autowired
    private AccountRepository databaseUserRepo;

    public AuthenticAccount findByUsername(String name){
        return new AuthenticAccount(Optional.ofNullable(databaseUserRepo.findByUsername(name)).orElseThrow(()->new UsernameNotFoundException("用户"+name+"不存在")));
    }
}
