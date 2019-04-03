package com.tl.o2o.dto;

import com.tl.o2o.entity.ProductCategory;
import com.tl.o2o.enums.ProductCategoryEnum;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductCategoryExecution {
    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    //类别数量
    private int count;

    //
    private ProductCategory productCategory;

    //类别列表
    private List<ProductCategory> productCategoryList;

    public ProductCategoryExecution(){

    }

    /**
     * 操作出现错误的时候使用的构造器
     * @param productCategoryEnum
     */
    public ProductCategoryExecution(ProductCategoryEnum productCategoryEnum){
        this.state = productCategoryEnum.getState();
        this.stateInfo = productCategoryEnum.getStateInfo();
    }

    /**
     * 操作成功时
     * @param stateEnum
     * @param productCategory
     */
    public ProductCategoryExecution(ProductCategoryEnum stateEnum, ProductCategory productCategory){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productCategory = productCategory;
    }

    /**
     * 类别操作成功时，返回ProductCategory列表
     * @param stateEnum
     * @param productCategoryList
     */
    public ProductCategoryExecution(ProductCategoryEnum stateEnum, List<ProductCategory> productCategoryList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productCategoryList = productCategoryList;
    }
}
