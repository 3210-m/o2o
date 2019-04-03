package com.tl.o2o.dao;

import com.tl.o2o.BaseTest;
import com.tl.o2o.entity.Area;
import com.tl.o2o.entity.PersonInfo;
import com.tl.o2o.entity.Shop;
import com.tl.o2o.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class ShopDaoTest extends BaseTest {
	@Autowired
	private ShopDao shopDao;


	@Test
	public void testQueryShopList(){
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(8L);
		shopCondition.setOwner(owner);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
		System.out.println("店铺数量"+shopList.size());
		System.out.println(shopDao.queryShopCount(shopCondition));
	}

	@Test
	public void getByShopId(){
		Shop shop = shopDao.queryByShopId(36l);
		System.out.println(shop.getArea().getAreaName());
        System.out.println(shop.getArea().getAreaId());
	}

	@Test
	public void insertShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(1);
		shop.setAdvice("审核中");
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1,effectedNum);

	}
	@Test
	public void updateShop() {
		Shop shop = new Shop();
		shop.setShopId(1L);

		shop.setShopDesc("测试描述");
		shop.setShopAddr("测试地址");
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1,effectedNum);

	}
}