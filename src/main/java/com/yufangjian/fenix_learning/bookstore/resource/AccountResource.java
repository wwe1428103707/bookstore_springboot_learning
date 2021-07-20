package com.yufangjian.fenix_learning.bookstore.resource;


import com.yufangjian.fenix_learning.bookstore.domain.account.validation.AuthenticatedAccount;
import com.yufangjian.fenix_learning.bookstore.domain.account.validation.NotConflictAccount;
import com.yufangjian.fenix_learning.bookstore.domain.account.validation.UniqueAccount;
import com.yufangjian.fenix_learning.bookstore.application.AccountApplicationService;
import com.yufangjian.fenix_learning.bookstore.domain.account.Account;
import com.yufangjian.fenix_learning.bookstore.infrastructure.jaxrs.CommonResponse;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/accounts")
@Component
@CacheConfig(cacheNames = "resource.account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountResource {

    @Inject
    private AccountApplicationService service;

    @GET
    @Path("/{username}")
    @Cacheable(key="#username")
    public Account getUser(@PathParam("username") String username){
        return service.findAccountByUserName(username);
    }


    @POST
    @CacheEvict(key = "#user.username")
    public Response createUser(@Valid @UniqueAccount Account user){
        return CommonResponse.op(() -> service.createAccount(user));
    }


    @PUT
    @CacheEvict(key="#user.username")
    public Response updateUser(@Valid @AuthenticatedAccount @NotConflictAccount Account user){
        return CommonResponse.op(()->service.updateAccount(user));
    }
}
