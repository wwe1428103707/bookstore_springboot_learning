package com.yufangjian.fenix_learning.bookstore.application.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yufangjian.fenix_learning.bookstore.domain.warehouse.Product;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Map;

@Getter
@Setter
public class Settlement {
    @Size(min = 1, message = "结算单中缺少商品清单")
    private Collection<Item> items;

    @NotNull(message = "结算单中缺少配送信息")
    private Purchase purchase;

    public transient Map<Integer, Product> productMap;

    @Getter
    @Setter
    public static class Item {
        @NotNull(message = "结算单中必须有明确的商品数量")
        @Min(value = 1, message = "结算单中商品数量至少为一件")
        private Integer amount;

        @JsonProperty("id")
        @NotNull(message = "结算单中必须有明确的商品信息")
        private Integer productId;
    }

    @Setter
    @Getter
    public static class Purchase {

        private Boolean delivery = true;

        @NotEmpty(message = "配送信息中缺少支付方式")
        private String pay;

        @NotEmpty(message = "配送信息中缺少收件人姓名")
        private String name;

        @NotEmpty(message = "配送信息中缺少收件人电话")
        private String telephone;

        @NotEmpty(message = "配送信息中缺少收件地址")
        private String location;
    }
}
