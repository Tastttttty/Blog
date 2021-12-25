package com.tasty.controller;

/*
 *@author:zhouxin
 *@date:2021/12/25 19:36
 *@description: 首页控制器
 */

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tasty.model.extraModel.queryvo.FirstPageBlog;
import com.tasty.model.extraModel.queryvo.RecommendBlog;
import com.tasty.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private BlogService blogService;

    //分页查询博客列表
    @GetMapping("/")
    public String index(Model model, @RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum, RedirectAttributes attributes){
        PageHelper.startPage(pageNum,10);
        //拿到首页博客和推荐文章
        List<FirstPageBlog> allFirstPageBlog = blogService.getAllFirstPageBlog();
        List<RecommendBlog> recommendedBlog = blogService.getRecommendedBlog();
        //设置分页格式
        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(allFirstPageBlog);
        System.out.println("pageInfo:" +pageInfo);
        //将首页博客和推荐博客返回给前端
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("recommendedBlogs", recommendedBlog);
        return "index";
    }

    //搜索博客
    @PostMapping("/search")
    public String search(Model model,
                         @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum,
                         @RequestParam String query) {
        PageHelper.startPage(pageNum, 1000);
        List<FirstPageBlog> searchBlog = blogService.getSearchBlog(query);

        PageInfo<FirstPageBlog> pageInfo = new PageInfo<>(searchBlog);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("query", query);
        return "search";
    }

    //博客信息统计
    @GetMapping("/footer/blogmessage")
    public String blogMessage(Model model){
        int blogTotal = blogService.getBlogTotal();
        int blogViewTotal = blogService.getBlogViewTotal();
        int blogCommentTotal = blogService.getBlogCommentTotal();
        int blogMessageTotal = blogService.getBlogMessageTotal();

        model.addAttribute("blogTotal",blogTotal);
        model.addAttribute("blogViewTotal",blogViewTotal);
        model.addAttribute("blogCommentTotal",blogCommentTotal);
        model.addAttribute("blogMessageTotal",blogMessageTotal);
        return "index :: blogMessage";
    }

}
