package com.wang.myblog.dao;


import com.wang.myblog.pojo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface BlogMapper {

    int saveBlogInfo(BlogInfo blogInfo);

    int saveBlogContent(BlogInfo blogInfo);

    int saveImageUrl(BlogInfo blogInfo);

    List<BlogInfo> blogList(@Param("limit") Integer limit,@Param("offset") Integer offset,@Param("username") String username);

    int totalCount(String username);

    List<BlogInfo> blogs();

    BlogInfo showBlog(Integer id);

    int getView(Integer id);

    void setView(@Param("id") Object id, @Param("view") Integer view);

    BlogInfo findBlog(Integer id);

    int update(BlogInfo blogInfo);

    int getTotalBlogs();

    int getUserTotalBlogs(String name);

    List<BlogInfo> getRecentBlogs();

    int saveComment(@Param("commentName") String commentName,@Param("comment") String comment,@Param("articleId") Integer articleId);

    List<Comment> getComments (Integer articleId);

    int getTotalComments();

    int getUserTotalComments(String username);

    List<Comment> commentList(@Param("limit") Integer limit,@Param("offset") Integer offset,@Param("username") String username);

    int saveReply(@Param("id") Integer id,@Param("replyComment") String replyComment);

    int delBlog(Integer id);

    int delComment(Integer id);

    void writeHistoricalViews(@Param("currentViews")Integer currentViews,@Param("tableName") String tableName);

    List<HistoricalViews> getHistoricalViews(@Param("tableName") String tableName);

    UserLike getLikeStatus(@Param("username")String username, @Param("articleId")Integer articleId);

    void writeUserViews2DB(@Param("username")String username,@Param("view") Integer view);
}
