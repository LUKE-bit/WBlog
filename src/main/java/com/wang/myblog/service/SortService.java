package com.wang.myblog.service;



import com.wang.myblog.dto.Result;
import com.wang.myblog.dto.TableList;
import com.wang.myblog.pojo.BlogInfo;
import com.wang.myblog.pojo.Sort;

import java.util.List;

public interface SortService {

    /**
     * 获得所有分类
     * @return
     */
    List<Sort> getAllSorts();

    /**
     *分类博客展示
     * @param sortId
     * @param pageNum
     * @return
     */
//    List<BlogInfo> sortBlog(Integer sortId, Integer pageNum);

    /**
     * 根据分类名称获得博客
     * @param sortName
     * @return
     */
    List<BlogInfo> sortToBlog(String sortName);

    /**
     * 添加分类
     * @param sortName
     * @return
     */
    Result addSort(String sortName);

    /**
     * 获得所有分类
     * @param limit
     * @param offset
     * @return
     */
    TableList sorts(Integer limit, Integer offset);

    List<Sort> editorSorts();
    /**
     * 删除分类
     * @param id
     * @return
     */
    Result delSort(Integer id);
}
