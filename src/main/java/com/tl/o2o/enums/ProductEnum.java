package com.tl.o2o.enums;

import lombok.Getter;

@Getter
public enum ProductEnum {
    CHECK(0,"审核中"),
    OFFLINE(-1,"非法商品"),
    SUCCESS(1,"操作成功"),
    PASS(2,"通过认证"),
    INNER_ERROR(-1001,"内部系统错误"),
    NULL_SHOPID(-1002,"productId为空"),
    NULL_SHOP(-1003,"已售完");

    private int state;
    private  String stateInfo;

    /**
     *
     * @param state
     * @param stateInfo
     */
    ProductEnum(int state,String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * 根据传入的state返回相应的enum值
     * @param state
     * @return
     */
    public static ProductEnum stateOf(int state){
        for(ProductEnum stateEnum:values()){
            if (stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }
}
