package com.tasty.service.Impl;

/*
 *@author:zhouxin
 *@date:2021/12/17 9:09
 *@description: 博客业务层接口实现类
 */

import com.tasty.dao.BlogDao;
import com.tasty.model.Blog;
import com.tasty.model.extraModel.queryvo.*;
import com.tasty.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao blogDao;

    /*保存博客 查询文章管理列表 删除博客 编辑博客 搜索博客管理列表*/

    @Transactional
    @Override
    public int saveBlog(Blog blog) {
        //设置博客基本参数 在新增博客中需要初始化创建时间、更新时间、浏览数量、访问数量
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
        blog.setViews(0);
        blog.setCommentCount(0);
        return blogDao.saveBlog(blog);
    }

    @Override
    public List<BlogQuery> getAllBlogQuery() {
        return blogDao.getAllBlogQuery();
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogDao.deleteBlog(id);
    }

    @Transactional
    @Override
    public int updateBlog(ShowBlog showBlog) {
        return blogDao.updateBlog(showBlog);
    }

    @Override
    public ShowBlog getBlogById(Long id) {
        return blogDao.getBlogById(id);
    }

    @Override
    public List<BlogQuery> searchByTitleAndType(SearchBlog searchBlog) {

            //这里一定要注意对应sql语句中的空指针异常,这是在java中解决的方法
        if(searchBlog.getTitle()!=null){
            searchBlog.setTitle("%"+searchBlog.getTitle()+"%");
        }
        else {
            searchBlog.setTitle("%%");
        }
        return blogDao.searchByTitleAndType(searchBlog);
    }

    //查询首页最新博客列表信息
    @Override
    public List<FirstPageBlog> getAllFirstPageBlog() {
        return blogDao.getFirstPageBlog();
    }

    //查询首页最新推荐信息
    @Override
    public List<RecommendBlog> getRecommendedBlog() {
        List<RecommendBlog> allRecommendBlog = blogDao.getAllRecommendBlog();
        return allRecommendBlog;
    }

    //搜索博客列表
    @Override
    public List<FirstPageBlog> getSearchBlog(String query) {
            //这里一定要注意对应sql语句中的空指针异常,这是在java中解决的方法
        if(query==null){
            query="%%";
        }
        else {
            query="%"+query+"%";
        }
        return blogDao.getSearchBlog(query);
    }

    //统计博客总数
    @Override
    public Integer getBlogTotal() {
        return blogDao.getBlogTotal();
    }

    //统计访问总数
    @Override
    public Integer getBlogViewTotal() {
        return blogDao.getBlogViewTotal();
    }

    //统计评论总数
    @Override
    public Integer getBlogCommentTotal() {
        return blogDao.getBlogCommentTotal();
    }

    //统计留言总数
    @Override
    public Integer getBlogMessageTotal() {
        return blogDao.getBlogMessageTotal();
    }
}
