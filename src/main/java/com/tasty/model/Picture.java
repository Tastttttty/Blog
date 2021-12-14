package com.tasty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 *@author:zhouxin
 *@date:2021/12/14 20:15
 *@description: 相册实体类 (t_picture)
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Picture {
    private Long id;
    private String picturename;
    private String picturetime;
    private String pictureaddress;
    private String picturedescription;
}
