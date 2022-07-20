package com.wang.myblog.service;



import com.wang.myblog.dto.Result;
import com.wang.myblog.pojo.BlogInfo;

import java.util.List;


public interface SearchService {

    /**
     * 根据searchKey查找文章是否存在
     */
    Result searchTitle(String searchKey);

    /**
     *博客搜索分页
     */
    List<BlogInfo> searchBlog(Integer pageNum, String searchKey);
}
