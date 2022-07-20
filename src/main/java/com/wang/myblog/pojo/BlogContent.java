package com.wang.myblog.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 博客内容
 */
@Data
public class BlogContent {
    private Integer id;
    private String articleContent;
    private Integer articleId;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createBy;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date modifiedBy;
}
