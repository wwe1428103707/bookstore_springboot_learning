package com.yufangjian.fenix_learning.bookstore.infrastructure.jaxrs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeMessage {
    public static final Integer CODE_SUCCESS = 0;

    public static final Integer CODE_DEFAULT_FAILURE = 1;

    private Integer code;
    private String message;
    private Object data;

    public CodeMessage(Integer code, String message){
        setCode(code);
        setMessage(message);
    }
}
