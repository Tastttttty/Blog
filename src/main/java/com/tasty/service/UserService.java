package com.tasty.service;

import com.tasty.model.User;

/*
 *@author:zhouxin
 *@date:2021/12/14 15:53
 *@description: 用户service层接口
 */
public interface UserService {
    User CheckUser(String username, String password);
}

/*
* 在service包下创建Impl包，用来放接口实现类，实现UserServiceImpl
* */