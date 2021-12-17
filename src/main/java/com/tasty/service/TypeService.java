package com.tasty.service;

import com.tasty.model.Type;

import java.util.List;

/*
 *@author:zhouxin
 *@date:2021/12/14 20:51
 *@description: 分类业务层接口
 */
public interface TypeService {
    /*查询分类、保存分类、修改编辑分类、删除分类、按照分类名称查找分类、按照分类id查找分类*/

    public List<Type> getAllType();

    int saveType(Type type);

    int updateType(Type type);

    void deleteType(Long id);

    Type getTypeByName(String name);

    Type getTypeById(Long id);
}
