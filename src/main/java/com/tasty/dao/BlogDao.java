package com.tasty.dao;

/*
 *@author:zhouxin
 *@date:2021/12/17 9:08
 *@description: 博客持久层接口
 */

import com.tasty.model.Blog;
import com.tasty.model.extraModel.queryvo.BlogQuery;
import com.tasty.model.extraModel.queryvo.SearchBlog;
import com.tasty.model.extraModel.queryvo.ShowBlog;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface BlogDao {

    /*保存博客 查询文章管理列表 删除博客 编辑博客 搜索博客管理列表*/

    int saveBlog(Blog blog);

    List<BlogQuery> getAllBlogQuery();

    void deleteBlog(Long id);

    int updateBlog(ShowBlog showBlog);//将修改好的ShowBlog传回给数据库，通过xml文件转换为Blog存储
    ShowBlog getBlogById(Long id);//从数据库t_blog查出对应的博客，修改其返回类型为ShowBlog

    List<BlogQuery> searchByTitleAndType(SearchBlog searchBlog);

}
