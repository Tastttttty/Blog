package com.tasty.service.Impl;

import com.tasty.dao.TypeDao;
import com.tasty.model.Type;
import com.tasty.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 *@author:zhouxin
 *@date:2021/12/14 20:54
 *@description: 分类业务层接口实现类
 */

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    /*查询分类、保存分类、修改编辑分类、删除分类、按照分类名称查找分类、按照分类id查找分类*/

    @Transactional
    @Override
    public List<Type> getAllType() {
        return typeDao.getAllType();
    }

    @Transactional
    @Override
    public int saveType(Type type) {
        return typeDao.saveType(type);
    }

    @Transactional
    @Override
    public int updateType(Type type) {
        return typeDao.updateType(type);
    }

    @Transactional
    @Override
    public void deleteType(Long id) {
        typeDao.deleteType(id);
    }

    @Transactional
    @Override
    public Type getTypeByName(String name) {
        return typeDao.getTypeByName(name);
    }

    @Transactional
    @Override
    public Type getTypeById(Long id) {
        return typeDao.getTypeById(id);
    }
}


//记得加@Transactional实现事务提交