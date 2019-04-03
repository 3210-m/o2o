package com.tl.o2o.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 店铺
 *
 * @author tangli
 * @create 2018-11-02 上午11:56
 **/
@Setter
@Getter
public class Shop {
    private Long shopId;
    private String shopName;
    private String shopDesc;    //描述
    private String shopAddr;
    private String phone;
    private String shopImg;
    private Integer priority;   //权重
    private Date createTime;
    private Date lastEditTime;
    private Integer enableStatus;   //店铺状态.-1不可用，0审核中,1可用
    private String advice;  //超级管理员给店家的建议

    private Area area;
    private PersonInfo owner;
    private ShopCategory shopCategory;


}
