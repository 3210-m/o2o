package com.tl.o2o.dao;

import com.tl.o2o.BaseTest;
import com.tl.o2o.entity.ProductCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class ProductCategoryDaoTest extends BaseTest {
    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Test
    public void testGetProductCtegorya(){
        List<ProductCategory> productCategoryList = productCategoryDao.getProductCategoryById(20L);
        System.out.println("ID:"+productCategoryList.get(0).getProductCategoryId()+"NAME:"+productCategoryList.get(0).getProductCategoryName());

    }

    @Test
    public void testInsertProductCategory(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setProductCategoryName("茶");
        productCategory.setProductCategoryDesc("各种茶，清新不腻");
        productCategory.setPriority(9);
        productCategory.setShopId(15L);
        productCategory.setCreateTime(new Date());
        productCategory.setLastEditTime(new Date());
        productCategoryDao.insertProductCategory(productCategory);
    }

    @Test
    public void testDeleteProductCategory(){
        productCategoryDao.deleteProductCategory(21L);
    }
}
