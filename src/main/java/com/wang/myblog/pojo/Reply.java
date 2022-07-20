package com.wang.myblog.pojo;

import lombok.Data;

import java.util.Date;


@Data
public class Reply {
    private Integer id;
    private Integer commentId;
    private String reply;
    private Date createBy;
    private Integer isDelete;
}
