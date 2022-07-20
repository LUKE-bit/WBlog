package com.wang.myblog.pojo;

import lombok.Data;

@Data
public class LikeCount {
    private Integer articleId;
    private Integer likeNum;


    public LikeCount(Integer articleId, Integer likeNum) {
        this.articleId = articleId;
        this.likeNum = likeNum;
    }
}
