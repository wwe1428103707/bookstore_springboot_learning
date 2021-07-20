package com.yufangjian.fenix_learning.bookstore.domain.account.validation;

import com.yufangjian.fenix_learning.bookstore.domain.auth.AuthenticAccount;
import com.yufangjian.fenix_learning.bookstore.domain.account.Account;
import com.yufangjian.fenix_learning.bookstore.domain.account.AccountRepository;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;
import java.util.Collection;
import java.util.function.Predicate;

public class AccountValidation<T extends Annotation> implements ConstraintValidator<T, Account> {

    @Inject
    protected AccountRepository repository;

    //Predicate接口主要用来判断一个参数是否符合要求
    protected Predicate<Account> predicate = c->true;

    @Override
    public boolean isValid(Account value, ConstraintValidatorContext context) {
        return repository == null || predicate.test(value);
    }

    public static class ExistsAccountValidator extends AccountValidation<ExistsAccount> {
        public void initialize(ExistsAccount constraintAnnotation) {
            predicate = c -> repository.existsById(c.getId());
        }
    }

    public static class AuthenticatedAccountValidator extends AccountValidation<AuthenticatedAccount>{
        public void initialize(AuthenticatedAccount constraintAnnotation){
            predicate = c -> {
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if ("anonymousUser".equals(principal)){
                    return false;
                } else{
                    AuthenticAccount loginUser = (AuthenticAccount) principal;
                    return c.getId().equals(loginUser.getId());
                }
            };
        }
    }

    public static class UniqueAccountValidator extends AccountValidation<UniqueAccount> {
        public void initialize(UniqueAccount constraintAnnotation) {
            predicate = c -> !repository.existsByUsernameOrEmailOrTelephone(c.getUsername(), c.getEmail(), c.getTelephone());
        }
    }

    public static class NotConflictAccountValidator extends AccountValidation<NotConflictAccount> {
        public void initialize(NotConflictAccount constraintAnnotation) {
            predicate = c -> {
                Collection<Account> collection = repository.findByUsernameOrEmailOrTelephone(c.getUsername(), c.getEmail(), c.getTelephone());
                // 将用户名、邮件、电话改成与现有完全不重复的，或者只与自己重复的，就不算冲突
                return collection.isEmpty() || (collection.size() == 1 && collection.iterator().next().getId().equals(c.getId()));
            };
        }
    }
}
