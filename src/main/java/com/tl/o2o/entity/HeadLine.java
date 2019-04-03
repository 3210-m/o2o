package com.tl.o2o.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 头条
 *
 * @author tangli
 * @create 2018-11-02 上午11:33
 **/
@Setter
@Getter
public class HeadLine {
    private Long lineId;
    private String lineName;
    private String lineLink;
    private String lineImg;
    private Integer priority;
    //0不可用，1可用
    private Integer enableStatus;
    private Date createTime;
    private Date lastEditTime;
}
