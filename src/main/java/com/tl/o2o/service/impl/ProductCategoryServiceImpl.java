package com.tl.o2o.service.impl;

import com.tl.o2o.dao.ProductCategoryDao;
import com.tl.o2o.dao.ProductDao;
import com.tl.o2o.dto.ProductCategoryExecution;
import com.tl.o2o.entity.ProductCategory;
import com.tl.o2o.enums.ProductCategoryEnum;
import com.tl.o2o.exception.CommonOperationException;
import com.tl.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    ProductCategoryDao productCategoryDao;

    @Autowired
    ProductDao productDao;

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
    @Transactional
    public ProductCategoryExecution deleteProductCategory(Long productCategoryId,Long shopId) throws CommonOperationException{
        if(productCategoryId<=0||shopId<=0){
            return new ProductCategoryExecution(ProductCategoryEnum.NULL_PRODUCTCATEGORY);
        }


        try {
            //先接触相关商品关联
            int effectNum = productDao.updateProductCategoryToNull(productCategoryId);
            if(effectNum<0){
                throw new CommonOperationException("商品类别更新失败");
            }
        }catch (Exception e){
            throw new CommonOperationException("updateProduct err:"+e.getMessage());
        }
        try {
            int effectNum = productCategoryDao.deleteProductCategory(productCategoryId,shopId);
            if(effectNum<0){
                throw new CommonOperationException("商品类别删除失败");
            }
        }catch (Exception e){
            throw new CommonOperationException("deleteProductCategory err:"+e.getMessage());
        }

        return new ProductCategoryExecution(ProductCategoryEnum.SUCCESS);
    }
}
