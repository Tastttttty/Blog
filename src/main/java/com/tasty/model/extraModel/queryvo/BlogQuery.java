package com.tasty.model.extraModel.queryvo;

/*
 *@author:zhouxin
 *@date:2021/12/17 9:28
 *@description: 后台查询列表实体类，用于管理博客的存在状态
 */

import com.tasty.model.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogQuery {
    private Long id;
    private String title;
    private Date updateTime;
    private Boolean recommend;
    private Boolean published;
    private Long typeId;
    private Type type;
}
