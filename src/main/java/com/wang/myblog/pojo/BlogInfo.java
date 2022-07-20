package com.wang.myblog.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 博客信息
 */
@Data
public class BlogInfo implements Serializable {
    private Integer id;
    private String author;
    private String sorts;
    private List<String> sortNames;
    private Integer sortId;
    private String articleTitle;
    private Integer articleViews;
    private String articleContent;
    private Integer articleId;
    private String imageUrl;
    private Date createBy;
    private String createByStr;
    private Date modifiedBy;
    private Integer likeNum;
}
