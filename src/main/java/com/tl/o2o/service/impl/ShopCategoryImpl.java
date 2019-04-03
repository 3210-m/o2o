package com.tl.o2o.service.impl;

import com.tl.o2o.dao.ShopCategoryDao;
import com.tl.o2o.entity.ShopCategory;
import com.tl.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tangli
 * @create 2018-12-02 下午7:26
 **/
@Service
public class ShopCategoryImpl implements ShopCategoryService {

	@Autowired
	private ShopCategoryDao shopCategoryDao;

	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategory) {
		return shopCategoryDao.queryShopCategory(shopCategory);
	}
}
