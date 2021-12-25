package com.tasty.service;

import com.tasty.model.Picture;

import java.util.List;

/*
 *@author:zhouxin
 *@date:2021/12/25 15:45
 *@description: 相册业务层接口实现
 */
public interface PictureService {
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
