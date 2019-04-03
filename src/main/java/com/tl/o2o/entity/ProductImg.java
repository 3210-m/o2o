package com.tl.o2o.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 详情图片
 *
 * @author tangli
 * @create 2018-11-02 下午2:18
 **/
@Getter
@Setter
public class ProductImg {
    private Long productImgId;
    private String imgAddr;
    private String imgDesc;
    private Integer priority;
    private Date createTime;
    private Long productId;
}
