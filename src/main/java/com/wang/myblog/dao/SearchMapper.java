package com.wang.myblog.dao;


import com.wang.myblog.pojo.BlogInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {

    List<BlogInfo> searchTitle(String searchKey);

    List<BlogInfo> searchBlogs(String searchKey);
}
