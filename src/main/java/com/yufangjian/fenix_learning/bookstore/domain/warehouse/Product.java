package com.yufangjian.fenix_learning.bookstore.domain.warehouse;

import com.yufangjian.fenix_learning.bookstore.domain.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Getter
@Setter
@Entity
public class Product extends BaseEntity {
    @NotEmpty(message = "商品名称不允许为空")
    private String title;

    @NotNull(message = "商品应当有明确的价格")
    @Min(value = 0, message = "商品价格最低为零")
    // 这里是偷懒，正式场合使用BigDecimal来表示金额
    private Double price;

    @Min(value = 0, message = "评分最低为0")
    @Max(value = 10, message = "评分最高为10")
    private Float rate;

    private String description;

    private String cover;

    private String detail;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Set<Specification> specifications;

}
