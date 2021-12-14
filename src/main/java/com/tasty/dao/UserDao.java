package com.tasty.dao;

import com.tasty.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/*
 *@author:zhouxin
 *@date:2021/12/14 11:40
 *@description:
 */
@Repository
@Mapper
public interface UserDao {
    User findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
