package com.tl.o2o.service;

import com.tl.o2o.entity.ShopCategory;

import java.util.List;

/**
 * @author tangli
 * @create 2018-12-02 下午7:24
 **/
public interface ShopCategoryService {
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategory);
}
