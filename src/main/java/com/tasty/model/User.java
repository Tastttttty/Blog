package com.tasty.model;

import lombok.Data;

import java.util.Date;

/*
 *@author:zhouxin
 *@date:2021/12/14 10:35
 *@description:用户实体类创建
 */
@Data
public class User {
    private long id;
    private String nickname;
    private String username;
    private String password;
    private String email;
    private String avatar;
    private Integer type;
    private Date createTime;
    private Date updateTime;
}
