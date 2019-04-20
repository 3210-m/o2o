package com.tl.o2o.service;

import com.tl.o2o.dto.ProductCategoryExecution;
import com.tl.o2o.entity.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    List<ProductCategory> getProductCategory(Long shopId);

    ProductCategoryExecution addProductCategory(ProductCategory productCategory);

    ProductCategoryExecution deleteProductCategory(Long productCategoryId,Long shopId);

}
