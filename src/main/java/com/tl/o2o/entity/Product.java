package com.tl.o2o.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 商品
 *
 * @author tangli
 * @create 2018-11-02 下午2:26
 **/
@Getter
@Setter
public class Product {
    private Long productId;
    private String productName;
    private String productDesc;
    private String imgAddr;     //缩略图地址
    private String normalPrice;     //原价
    private String promotionPrice;      //折扣价
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
    private Integer enableStatus;   //商品状态：0下架,1展示

    private List<ProductImg> productImgList;    //详情图列表
    private ProductCategory productCategory;
    private Shop shop;
}
