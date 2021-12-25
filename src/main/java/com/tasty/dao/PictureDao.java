package com.tasty.dao;

import com.tasty.model.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *@author:zhouxin
 *@date:2021/12/25 15:42
 *@description:相册dao接口实现
 */

@Mapper
@Repository
public interface PictureDao {

    //查询照片
    List<Picture> listPicture();

    //添加图片
    int savePicture(Picture picture);

    //根据id查询照片
    Picture getPicture(Long id);

    //编辑修改相册
    int updatePicture(Picture picture);

    //删除照片
    void deletePicture(Long id);
}
