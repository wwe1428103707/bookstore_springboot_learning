package com.yufangjian.fenix_learning.bookstore.domain.warehouse;

import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Collection<Product> findByIdIn(Collection<Integer> ids);
}
