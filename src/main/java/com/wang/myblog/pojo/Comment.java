package com.wang.myblog.pojo;

import lombok.Data;

import java.util.Date;


@Data
public class Comment {
    private Integer id;
    private Integer articleId;
    private String commentName;
    private String comment;
    private String articleTitle;
    private String reply;
    private Date createBy;
    private String createByStr;
    private Date replyCreateBy;
    private Integer isDelete;
}
