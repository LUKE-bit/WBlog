package com.wang.myblog.dto;

import lombok.Data;


@Data
public class Result {
    private int code;
    private String message;
    private Object data;
}
