package com.tl.o2o.enums;


import lombok.Getter;

@Getter
public enum ProductCategoryEnum {


    SUCCESS(1,"操作成功"),
    INNER_ERROR(-1001,"内部系统错误"),
    INSERT_ERROR(-1002,"插入店铺失败"),
    NULL_PRODUCTCATEGORY(-1003,"信息为空"),
    NULL_SHOPID(-1004,"请先登录");

    private int state;
    private  String stateInfo;

    /**
     *
     * @param state
     * @param stateInfo
     */
    ProductCategoryEnum(int state,String stateInfo){
        this.state = state;
        this.stateInfo = stateInfo;
    }

    /**
     * 根据传入的state返回相应的enum值
     * @param state
     * @return
     */
    public static ProductCategoryEnum stateOf(int state){
        for(ProductCategoryEnum stateEnum:values()){
            if (stateEnum.getState() == state){
                return stateEnum;
            }
        }
        return null;
    }


}
