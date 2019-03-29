package com.lee.cloudadmin.domain;

import java.util.Date;

/**
 * token
 */
public class UserToken {
    private Long userId;
    private String token;
    private Date expireTime;
    private Date updateTime;

    @Override
    public String toString() {
        return "UserToken{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                ", expireTime=" + expireTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public UserToken(Long userId, String token, Date expireTime, Date updateTime) {
        this.userId = userId;
        this.token = token;
        this.expireTime = expireTime;
        this.updateTime = updateTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
