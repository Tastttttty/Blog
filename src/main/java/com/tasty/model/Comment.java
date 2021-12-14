package com.tasty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 *@author:zhouxin
 *@date:2021/12/14 19:15
 *@description: 评论实体类 (t_comment)
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    //自身属性
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    private Date createTime;
    private Long blogId;
    private Long parentCommentId;
    private Boolean adminComment;

    //评论的回复
/*
回复评论集合（replyComments）用来存储回复信息
父评论昵称（parentNickname）用来设置父级评论的id
父评论（parentComment）用来显示父级评论姓名
*/
    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;
    private String parentNickname;
}
