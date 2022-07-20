package com.wang.myblog.utils;


public class RedisKeyUtils {
    public static final String USER_LIKE = "user_like";
    public static final String LIKE_COUNT = "like_count";
    public static final String USER_VIEWS = "user_views";
    public static final String HISTORICAL_VIEWS = "historical_views";
    public static final String BLOG_NUM = "blog_num";
    public static final String LIKE_NUM = "like_num";
    public static final String BLOG = "blog";
    public static final String VIEW = "view";

    public static String getLikeKey(String username,Integer articleId){
        StringBuilder builder = new StringBuilder();
        builder.append(username);
        builder.append(":");
        builder.append(articleId);
        return builder.toString();
    }
}
