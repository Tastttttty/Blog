package com.tasty.model;

/*
 *@author:zhouxin
 *@date:2021/12/14 18:26
 *@description: Blog实体类
 */


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
* 注意
* blog除了自身的属性之外，博客和分类是多对一的关系，博客和用户是多对一的关系，博客和评论是一对多的关系，
* 在这些对应关系中，博客表和分类表、用户表、评论表都有关联，
* 这里就要涉及到Mybatis的多对一和一对多的关系了
* */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    //自身属性
    private Long id;
    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    private Integer commentCount;
    private Boolean appreciation;
    private Boolean shareStatement;
    private Boolean commentabled;
    private Boolean published;
    private Boolean recommend;
    private Date createTime;
    private Date updateTime;

 /*
分类（type）、用户（user）、评论集合（comments）以及分类id（typeId）、用户id（userId），
用来实现Mybatis的多表查询和相关功能。
*/
    //延伸属性
    private Type type;
    private User user;
    private Long typeId;
    private Long userId;
    private List<Comment> comments = new ArrayList<>();
}
