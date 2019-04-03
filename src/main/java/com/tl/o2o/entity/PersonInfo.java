package com.tl.o2o.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户信息
 *
 * @author tangli
 * @create 2018-11-02 上午12:06
 **/
@Getter
@Setter
public class PersonInfo {
    private Long userId;
    private String name;
    private String profileImg;
    private String email;
    private String gender;
    private Integer enableStatus;
    //1.顾客，2.店家，3.超级管理员
    private Integer userType;
    private Date createTime;
    private Date lastEditTime;
}
