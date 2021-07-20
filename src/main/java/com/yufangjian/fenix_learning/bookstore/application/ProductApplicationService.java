package com.yufangjian.fenix_learning.bookstore.application;

import com.yufangjian.fenix_learning.bookstore.domain.payment.Stockpile;
import com.yufangjian.fenix_learning.bookstore.domain.payment.StockpileService;
import com.yufangjian.fenix_learning.bookstore.domain.warehouse.Product;
import com.yufangjian.fenix_learning.bookstore.domain.warehouse.ProductService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

@Named
@Transactional
public class ProductApplicationService {
    @Inject
    private ProductService service;

    @Inject
    private StockpileService stockpileService;

    public Iterable<Product> getAllProducts() {
        return service.getAllProducts();
    }

    public Product getProduct(Integer id) {
        return service.getProduct(id);
    }

    public Product saveProduct(Product product) {
        return service.saveProduct(product);
    }

    public void removeProduct(Integer id) {
        service.removeProduct(id);
    }

    public Stockpile getStockpile(Integer productId) {
        return stockpileService.getByProductId(productId);
    }

    public void setStockpileAmountByProductId(Integer productId, Integer amount) {
        stockpileService.set(productId, amount);
    }
}
