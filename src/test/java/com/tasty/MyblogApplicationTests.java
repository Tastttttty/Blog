package com.tasty;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tasty.dao.UserDao;
import com.tasty.model.Type;
import com.tasty.service.BlogService;
import com.tasty.service.Impl.UserServiceImpl;
import com.tasty.service.TypeService;
import com.tasty.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class MyblogApplicationTests {

    @Autowired
    UserService userService;

    @Autowired
    TypeService typeService;

    @Autowired
    BlogService blogService;

    @Test
    public void testUser(){
        System.out.println(userService.CheckUser("admin", "123456"));
        System.out.println("------------------------------------------------------------------------------------");
    }

    @Test
    public void testType(){
        System.out.println(typeService.getTypeByName("Tech"));
    }

//    测试pageInfo的属性
    @Test
    public void testPage(){
        List<Type> list = typeService.getAllType();
        PageHelper.startPage(1, 10, "id desc");
        PageInfo<Type> pageInfo = new PageInfo<Type>(list);
        System.out.println(pageInfo.getList());
    }

//    测试Blog层
    @Test
    public void testBlog(){
        System.out.println(blogService.getAllBlogQuery()+"--------------");
    }
}
