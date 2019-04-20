package com.tl.o2o.dao;

import com.tl.o2o.entity.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryDao {
    /**
     * 根据shopId获得商品类别信息
     * @param shopId
     * @return
     */
    List<ProductCategory> getProductCategoryById(Long shopId);

    /**
     * 增加商品类别
     * @param productCategory
     * @return
     */
    int insertProductCategory(ProductCategory productCategory);

    /**
     * 单用户 不验证
     * @param productCategoryId
     */
    int deleteProductCategory(@Param("productCategoryId") Long productCategoryId, @Param("shopId") Long ShopId);
}
