package com.tl.o2o.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 微信账号
 *
 * @author tangli
 * @create 2018-11-02 上午11:04
 **/
@Getter
@Setter
public class WechatAuth {
    private Long wechatAuthId;
    private String openId;
    private Date createTime;
    private PersonInfo personInfo;
}
