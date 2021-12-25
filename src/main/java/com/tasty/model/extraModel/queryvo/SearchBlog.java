package com.tasty.model.extraModel.queryvo;

/*
 *@author:zhouxin
 *@date:2021/12/20 10:05
 *@description: 用来作为博客列表查询的实体类
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchBlog {
    private String title;
    private Long typeId;
}
