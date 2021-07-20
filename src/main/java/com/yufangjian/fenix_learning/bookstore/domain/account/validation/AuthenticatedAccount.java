package com.yufangjian.fenix_learning.bookstore.domain.account.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({FIELD, METHOD, PARAMETER, TYPE})
@Constraint(validatedBy = AccountValidation.AuthenticatedAccountValidator.class)
public @interface AuthenticatedAccount {
    String message() default "非当前用户";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

