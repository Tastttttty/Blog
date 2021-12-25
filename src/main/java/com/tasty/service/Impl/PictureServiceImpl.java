package com.tasty.service.Impl;

import com.tasty.dao.PictureDao;
import com.tasty.model.Picture;
import com.tasty.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 *@author:zhouxin
 *@date:2021/12/25 15:46
 *@description: 相册业务层实体类实现
 */

@Service
public class PictureServiceImpl implements PictureService {

    @Autowired
    private PictureDao pictureDao;

    @Override
    public List<Picture> listPicture() {
        return pictureDao.listPicture();
    }

    @Override
    public int savePicture(Picture picture) {
        return pictureDao.savePicture(picture);
    }

    @Override
    public Picture getPicture(Long id) {
        return pictureDao.getPicture(id);
    }

    @Override
    public int updatePicture(Picture picture) {
        return pictureDao.updatePicture(picture);
    }

    @Override
    public void deletePicture(Long id) {
        pictureDao.deletePicture(id);
    }
}
