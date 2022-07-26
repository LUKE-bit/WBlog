package com.wang.myblog.service;



import com.wang.myblog.dto.Result;
import com.wang.myblog.dto.TableList;
import com.wang.myblog.pojo.BlogInfo;
import com.wang.myblog.pojo.Comment;

import java.util.List;
import java.util.Map;


public interface BlogService {

    /**
     * 保存文章
     * @param blogInfo
     * @return
     */
    Result saveBlog(BlogInfo blogInfo);

    /**
     * 分页获得文章列表
     * @param limit
     * @param offset
     * @return
     */
    TableList blogList(Integer limit, Integer offset);

    /**
     * 编辑文章
     * @param id
     * @return
     */
    BlogInfo editBlog(Integer id);

    /**
     * 获得指定页数的文章集合
     * @param pageNum
     * @return
     */
    List<BlogInfo> blogList(Integer pageNum);

    /**
     * 文章的详细信息
     * @param id
     * @return
     */
    BlogInfo showBlog(Integer id);

    int getView(Integer id);

    /**
     * 获得总浏览量
     * @return
     */
    int getTotalView();

    /**
     * 获得用户的总浏览量
     * @return
     */
    int getUserTotalViews();

    /**
     * 根据Id查找文章
     * @param id
     * @return
     */
    BlogInfo findBlog(Integer id);

    /**
     *
     * @param blogInfo
     * @return
     */
    Result update( BlogInfo blogInfo);

    int getTotalBlogs();

    int getUserTotalBlogs();

    List<BlogInfo> getRecentBlogs();

    List<BlogInfo> getMostViewsBlogs();

    Result saveComment(String commentName,String comment,Integer articleId);

    List<Comment> getComments (Integer articleId, Integer pageNum);

    int getTotalComments();

    int getUserTotalComments();

    TableList commentList(Integer limit, Integer offset);

    Result saveReply(Integer id,String replyComment);

    Result delBlog(Integer id);

    Result delComment(Integer id);

    Map<String,Integer> getHistoricalViews(String key);

    void writeHistoricalViews();

    void setView();

    void writeUserViews2DB();
}
