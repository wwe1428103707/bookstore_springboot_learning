package com.yufangjian.fenix_learning.bookstore.domain.payment;

import com.yufangjian.fenix_learning.bookstore.domain.warehouse.Product;
import com.yufangjian.fenix_learning.bookstore.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Stockpile extends BaseEntity {
    private Integer amount;

    private Integer frozen;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private transient Product product;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void frozen(Integer number) {
        this.amount -= number;
        this.frozen += number;
    }

    public void thawed(Integer number) {
        frozen(-1 * number);
    }

    public void decrease(Integer number) {
        this.frozen -= number;
    }

    public void increase(Integer number) {
        this.amount += number;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
