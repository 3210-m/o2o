package com.tl.o2o.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 本地账号
 *
 * @author tangli
 * @create 2018-11-02 上午11:07
 **/
@Getter
@Setter
public class LocalAuth {
    private Long localAuthId;
    private String username;
    private String password;
    private Date createTime;
    private Date lastEditTime;
    private PersonInfo personInfo;
}
