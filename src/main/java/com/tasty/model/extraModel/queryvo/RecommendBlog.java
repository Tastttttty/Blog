package com.tasty.model.extraModel.queryvo;

/*
 *@author:zhouxin
 *@date:2021/12/25 19:26
 *@description: 最新推荐实体类
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendBlog {
    private Long id;
    private String title;
    private String firstPicture;
    //是否推荐
    private boolean recommend;
}
