package com.tl.o2o.dao;

import com.tl.o2o.BaseTest;
import com.tl.o2o.entity.Area;
import com.tl.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;//.Assert.assertEquals;

/**
 * @author tangli
 * @create 2018-11-26 下午2:49
 **/
public class ShopCategoryDaoTest extends BaseTest {
	@Autowired
	ShopCategoryDao shopCategoryDao;

	@Test
	public void testQueryShopCategory(){
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
		assertEquals(12,shopCategoryList.size());
		ShopCategory testCategory = new ShopCategory();
		ShopCategory parentCategory = new ShopCategory();
		parentCategory.setShopCategoryId(1L);
		testCategory.setParent(parentCategory);
		//List<ShopCategory> shopCategoryList = shopCategoryDao.querySopCategory(testCategory);
		//assertEquals("咖啡",shopCategoryList.get(0).getShopCategoryName());
	}
}
