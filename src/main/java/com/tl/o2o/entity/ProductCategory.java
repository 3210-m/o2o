package com.tl.o2o.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 商品类别
 *
 * @author tangli
 * @create 2018-11-02 下午12:39
 **/
@Setter
@Getter
public class ProductCategory {
    private Long productCategoryId;
    private Long shopId;
    private String productCategoryName;
    private String productCategoryDesc;
    private Integer priority;
    private Date createTime;
    private Date lastEditTime;
}
