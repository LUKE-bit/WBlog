package com.wang.myblog.dao;


import com.wang.myblog.pojo.BlogInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArchiveMapper {

    List<String> findAchiveByYear();

    /**
     * 查找年份对应的博客数
     * @param year
     * @return
     */
    int findYearByNum(String year);

    List<BlogInfo> findByYear(String year);
}
