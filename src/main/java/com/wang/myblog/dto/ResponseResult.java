package com.wang.myblog.dto;

import lombok.Data;

/**
 * MarkDown文件上传
 */
@Data
public class ResponseResult {
    private Integer success;
    private String Message;
    private String url;
}
