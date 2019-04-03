package com.tl.o2o.service.impl;

import com.tl.o2o.dao.ProductCategoryDao;
import com.tl.o2o.dto.ProductCategoryExecution;
import com.tl.o2o.entity.ProductCategory;
import com.tl.o2o.enums.ProductCategoryEnum;
import com.tl.o2o.exception.CommonOperationException;
import com.tl.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Override
    public List<ProductCategory> getProductCategory(Long shopId) {
        return productCategoryDao.getProductCategoryById(shopId);
    }

    @Override
    public ProductCategoryExecution addProductCategory(ProductCategory productCategory) {
        if(productCategory==null){
            return new ProductCategoryExecution(ProductCategoryEnum.NULL_PRODUCTCATEGORY);
        }
        try{
            //增加初始信息
            productCategory.setCreateTime(new Date());
            productCategory.setLastEditTime(new Date());

            int effectedNum = productCategoryDao.insertProductCategory(productCategory);
            if(effectedNum<=0){
                throw new CommonOperationException("创建商品类别失败");
            }
        }catch (Exception e){
            throw new CommonOperationException("addProductCategory err:"+e.getMessage());
        }
        return new ProductCategoryExecution(ProductCategoryEnum.SUCCESS, productCategory);
    }

    @Override
    public ProductCategoryExecution deleteProductCategory(Long productCategoryId) {
        if(productCategoryId<=0){
            return new ProductCategoryExecution(ProductCategoryEnum.NULL_PRODUCTCATEGORY);
        }

        try {
            productCategoryDao.deleteProductCategory(productCategoryId);
        }catch (Exception e){
            throw new CommonOperationException("deleteProductCategory err:"+e.getMessage());
        }
        return new ProductCategoryExecution(ProductCategoryEnum.SUCCESS);
    }
}
