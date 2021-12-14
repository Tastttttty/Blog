package com.tasty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/*
 *@author:zhouxin
 *@date:2021/12/14 19:11
 *@description: 分类实体类 (t_type)
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Type {
//      自身属性
     private Long id;
     private String name;

//     该类型下的所有博客
     private List<Blog> blogs = new ArrayList<>();
}
