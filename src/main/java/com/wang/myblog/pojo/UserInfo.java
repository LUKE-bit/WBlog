package com.wang.myblog.pojo;

import lombok.Data;

/**
 * 管理员信息
 */
@Data
public class UserInfo {
    private Integer id;
    private String email;
    private String login_password;
    private String nick_name;
    private String login_role;

    private Integer status;

    public UserInfo(String email, String loginPassword, String nickName) {
        this.email = email;
        this.login_password = loginPassword;
        this.nick_name = nickName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginPassword() {
        return login_password;
    }

    public void setLoginPassword(String loginPassword) {
        this.login_password = loginPassword;
    }

    public String getNickName() {
        return nick_name;
    }

    public void setNickName(String nickName) {
        this.nick_name = nickName;
    }

    public String getLoginRole() {
        return login_role;
    }

    public void setLoginRole(String loginRole) {
        this.login_role = loginRole;
    }
}
