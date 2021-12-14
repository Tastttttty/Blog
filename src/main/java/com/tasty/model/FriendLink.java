package com.tasty.model;

/*
 *@author:zhouxin
 *@date:2021/12/14 20:11
 *@description: 友链实体类  (t_friend)
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FriendLink {
    //自身属性
    private Long id;
    private String blogname;
    private String blogaddress;
    private String pictureaddress;
    private Date createTime;
}
