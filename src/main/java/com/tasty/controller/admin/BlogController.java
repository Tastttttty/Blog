package com.tasty.controller.admin;

/*
 *@author:zhouxin
 *@date:2021/12/17 10:32
 *@description: 博客controller层实现
 */

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tasty.model.Blog;
import com.tasty.model.User;
import com.tasty.model.extraModel.queryvo.BlogQuery;
import com.tasty.model.extraModel.queryvo.SearchBlog;
import com.tasty.model.extraModel.queryvo.ShowBlog;
import com.tasty.service.BlogService;
import com.tasty.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {

    //controller层调用业务层
    @Autowired
    private BlogService blogService;
    @Autowired
    private TypeService typeService;


    //首页显示
    @RequestMapping("/blogs")
    public String blogs(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        String orderBy = "update_time desc";
        PageHelper.startPage(pageNum, 10, orderBy);//第几页 一页多少个数据 排列顺序
        List<BlogQuery> list = blogService.getAllBlogQuery();
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(list);
        model.addAttribute("types", typeService.getAllType());
        model.addAttribute("pageInfo", pageInfo);
        return "admin/blogs";
    }

    //跳转至新增页面
    @RequestMapping("/blogs/input")
    public String toAdd(Model model){
        model.addAttribute("blog", new Blog());
        model.addAttribute("types", typeService.getAllType());
        return "admin/blogs-input";
    }

    //提交新增页面内容
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        //新增的时候需要传递blog对象，blog对象需要有user
        blog.setUser((User) session.getAttribute("user"));
        //设置blog的type
        blog.setType(typeService.getTypeById(blog.getType().getId()));
        //设置blog中typeId属性
        blog.setTypeId(blog.getType().getId());
        //设置用户id
        blog.setUserId(blog.getUser().getId());

        int b = blogService.saveBlog(blog);
        if(b == 0){
            attributes.addFlashAttribute("message", "新增失败");
        }else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/blogs";
    }

    //删除博客
    @RequestMapping("/blogs/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes attributes){
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/blogs";
    }

    //博客编辑
    @RequestMapping("/blogs/{id}/input")
    public String edit(@PathVariable("id") Long id, Model model){
        ShowBlog showBlog = blogService.getBlogById(id);
        //将要修改的showBlog和所有分类传给前端页面，
        model.addAttribute("blog", showBlog);
        model.addAttribute("types", typeService.getAllType());
        return "admin/blogs-input";
    }

    @PostMapping("/blogs/{id}")
    public String editpost(@Valid ShowBlog showBlog,RedirectAttributes attributes){
        int i = blogService.updateBlog(showBlog);
        if(i == 0){
            attributes.addFlashAttribute("message", "修改失败");
        }else {
            attributes.addFlashAttribute("message", "修改成功");
        }
        return "redirect:/admin/blogs";
    }

    //博客列表搜索
    @PostMapping("/blogs/search")
    public String search(SearchBlog searchBlog, Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum){
        List<BlogQuery> blogs = blogService.searchByTitleAndType(searchBlog);
        PageHelper.startPage(pageNum, 10);
        PageInfo<BlogQuery> pageInfo = new PageInfo<>(blogs);
        model.addAttribute("pageInfo", pageInfo);
                //这里的::对应前端页面的 th:fragment="blogList"  每次只刷新一部分
        return "admin/blogs :: blogList";
    }
}
