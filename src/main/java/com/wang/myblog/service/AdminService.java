package com.wang.myblog.service;


import com.wang.myblog.dto.Result;
import com.wang.myblog.pojo.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface AdminService extends UserDetailsService {

    /**
     * 用户注册
     * @param userInfo
     * @return
     */
    Result register(UserInfo userInfo);

    /**
     * 用户名是否已经注册
     * @param username
     * @return
     */
    Integer isRegistered(String username);

    /**
     * 更新激活状态
     * @param username
     */
    void updateStatus(String username);

    /**
     * 根据用户名获得邮箱
     * @param username
     * @return
     */
    String getEmailByUserName(String username);

    /**
     * 邮箱是否被注册
     * @param email
     * @return
     */
    Integer isEmail(String email);
}
