package com.tl.o2o.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 地区
 *
 * @author tangli
 * @create 2018-11-01 下午11:35
 **/
@Getter
@Setter
public class Area {
    private Integer areaId;
    private String areaName;
    private Integer priority;
    private Date createTime;
    private Date lasteTime;


}
