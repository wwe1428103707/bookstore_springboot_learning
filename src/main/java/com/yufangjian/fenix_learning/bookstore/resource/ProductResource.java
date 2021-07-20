package com.yufangjian.fenix_learning.bookstore.resource;

import com.yufangjian.fenix_learning.bookstore.domain.auth.Role;
import com.yufangjian.fenix_learning.bookstore.domain.payment.Stockpile;
import com.yufangjian.fenix_learning.bookstore.domain.warehouse.Product;
import com.yufangjian.fenix_learning.bookstore.application.ProductApplicationService;
import com.yufangjian.fenix_learning.bookstore.infrastructure.jaxrs.CommonResponse;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Component;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/products")
@Component
@CacheConfig(cacheNames = "resource.product")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {
    @Inject
    ProductApplicationService service;

    @GET
    @Cacheable(key = "'ALL_PRODUCT'")
    public Iterable<Product> getAllProducts() {
        return service.getAllProducts();
    }

    @GET
    @Path("/{id}")
    @Cacheable(key = "#id")
    public Product getProduct(@PathParam("id") Integer id) {
        return service.getProduct(id);
    }

    @PUT
    @Caching(evict = {
            @CacheEvict(key = "#product.id"),
            @CacheEvict(key = "'ALL_PRODUCT'")
    })
    @RolesAllowed(Role.ADMIN)
    public Response updateProduct(@Valid Product product) {
        return CommonResponse.op(() -> service.saveProduct(product));
    }

    @POST
    @Caching(evict = {
            @CacheEvict(key = "#product.id"),
            @CacheEvict(key = "'ALL_PRODUCT'")
    })
    @RolesAllowed(Role.ADMIN)
    public Product createProduct(@Valid Product product) {
        return service.saveProduct(product);
    }

    @DELETE
    @Path("/{id}")
    @Caching(evict = {
            @CacheEvict(key = "#id"),
            @CacheEvict(key = "'ALL_PRODUCT'")
    })
    @RolesAllowed(Role.ADMIN)
    public Response removeProduct(@PathParam("id") Integer id) {
        return CommonResponse.op(() -> service.removeProduct(id));
    }

    @PATCH
    @Path("/stockpile/{productId}")
    @RolesAllowed(Role.ADMIN)
    public Response updateStockpile(@PathParam("productId") Integer productId, @QueryParam("amount") Integer amount) {
        return CommonResponse.op(() -> service.setStockpileAmountByProductId(productId, amount));
    }

    @GET
    @Path("/stockpile/{productId}")
    @RolesAllowed(Role.ADMIN)
    public Stockpile queryStockpile(@PathParam("productId") Integer productId) {
        return service.getStockpile(productId);
    }
}
