package com.yufangjian.fenix_learning.bookstore.domain.account;

import com.yufangjian.fenix_learning.bookstore.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;




@Entity
@Getter
@Setter
public class Account extends BaseEntity {
    @NotEmpty(message = "用户名必须存在")
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(updatable = false)
    private String password;

    @NotEmpty(message = "notice NAME not null!")
    private String name;

    private String avatar;

    @Pattern(regexp = "1\\d{10}", message = "wrong Phone number")
    private String telephone;

    @Email(message = "wrong Email")
    private String email;

    private String location;
}
