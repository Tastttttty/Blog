package com.tasty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 *@author:zhouxin
 *@date:2021/12/14 20:03
 *@description: 留言实体类 (t_message)
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    //自身属性
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;
    private Long adminMessageId;
    private Boolean adminMessage;

    //回复留言
    private List<Message> replyMessages = new ArrayList<>();
    private Message parentMessage;
    private String parentNickname;
}
