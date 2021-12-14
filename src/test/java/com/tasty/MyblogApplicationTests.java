package com.tasty;

import com.tasty.dao.UserDao;
import com.tasty.service.Impl.UserServiceImpl;
import com.tasty.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyblogApplicationTests {

    @Autowired
    UserService userService;

    @Test
    public void test1(){
        System.out.println(userService.CheckUser("admin", "123456"));
    }
}
