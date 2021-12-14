package com.tasty.service.Impl;

import com.tasty.dao.UserDao;
import com.tasty.model.User;
import com.tasty.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 *@author:zhouxin
 *@date:2021/12/14 15:54
 *@description:用户service层接口实现类
 */
@Service
public class UserServiceImpl implements UserService {

//    service层调dao层
    @Autowired
    private UserDao userDao;

    @Override
    public User CheckUser(String username, String password) {
        User user = userDao.findByUsernameAndPassword(username, password);
        return  user;
    }
}
/*
* 这里主要是获取数据库中的用户名和密码，通过控制器传递过来的密码进行解析匹配，匹配成功则登录
* */
