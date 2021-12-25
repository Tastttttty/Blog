package com.tasty.service.Impl;

/*
 *@author:zhouxin
 *@date:2021/12/17 9:09
 *@description: 博客业务层接口实现类
 */

import com.tasty.dao.BlogDao;
import com.tasty.model.Blog;
import com.tasty.model.extraModel.queryvo.BlogQuery;
import com.tasty.model.extraModel.queryvo.SearchBlog;
import com.tasty.model.extraModel.queryvo.ShowBlog;
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
}
