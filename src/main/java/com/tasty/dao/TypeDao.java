package com.tasty.dao;

import com.tasty.model.Type;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *@author:zhouxin
 *@date:2021/12/14 20:21
 *@description: Type类持久层接口
 */

@Mapper
@Repository
public interface TypeDao {

    /*查询分类、保存分类、修改编辑分类、删除分类、按照分类名称查找分类、按照分类id查找分类*/

    public List<Type> getAllType();

    int saveType(Type type);

    int updateType(Type type);

    void deleteType(Long id);

    Type getTypeByName(String name);

    Type getTypeById(Long id);
}
