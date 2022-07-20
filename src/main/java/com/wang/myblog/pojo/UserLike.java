package com.wang.myblog.pojo;

import lombok.Data;

@Data
public class UserLike {
    private Integer id;
    private String username;
    private Integer articleId;
    private Integer likeStatus;



    public UserLike(String username, Integer articleId, Integer likeStatus) {
        this.username = username;
        this.articleId = articleId;
        this.likeStatus = likeStatus;
    }
}
