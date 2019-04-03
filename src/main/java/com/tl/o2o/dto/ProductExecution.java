package com.tl.o2o.dto;

import com.tl.o2o.entity.Product;
import com.tl.o2o.enums.ProductEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductExecution {
    //结果状态
    private int state;

    //状态标识
    private String stateInfo;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    //商品数量
    private int count;

    //
    private Product product;

    //商品列表
    private List<Product> productList;

    public ProductExecution(){

    }

    /**
     * 操作出现错误的时候使用的构造器
     * @param productEnum
     */
    public ProductExecution(ProductEnum productEnum){
        this.state = productEnum.getState();
        this.stateInfo = productEnum.getStateInfo();
    }

    /**
     * 操作成功时
     * @param stateEnum
     * @param product
     */
    public ProductExecution(ProductEnum stateEnum, Product product){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.product = product;
    }

    /**
     * 店铺操作成功时，返回Product列表
     * @param stateEnum
     * @param productList
     */
    public ProductExecution(ProductEnum stateEnum, List<Product> productList){
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.productList = productList;
    }
}
