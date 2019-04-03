package com.tl.o2o.service;

import com.tl.o2o.BaseTest;
import com.tl.o2o.dto.ShopExecution;
import com.tl.o2o.entity.Area;
import com.tl.o2o.entity.PersonInfo;
import com.tl.o2o.entity.Shop;
import com.tl.o2o.entity.ShopCategory;
import com.tl.o2o.enums.ShopStateEnum;
import com.tl.o2o.exception.ShopOperationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class ShopServiceTest extends BaseTest {
	@Autowired
	private ShopService shopService;

	@Test
	public void testGetShopList(){
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(8L);
		shopCondition.setOwner(owner);
		ShopExecution se = shopService.getShopList(shopCondition, 3, 5);
		System.out.println("店铺数量"+se.getShopList().size()+",count:"+se.getCount());
	}

	@Test
	public void modifyShop()throws ShopOperationException,FileNotFoundException {
		Shop shop = new Shop();
		shop.setShopId(36l);
		shop.setShopName("modifys");
		File shopImg = new File("D:/prijectdev/image/tududu.jpg");
		InputStream inputStream = new FileInputStream(shopImg);
		ShopExecution shopExecution = shopService.modifyShop(shop,inputStream,"tududu.jpg");
		System.out.println("new path  :  "+ shopExecution.getShop().getShopImg());
	}

	@Test
	public void addShop() throws FileNotFoundException {
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
		shop.setShopName("测试3");

		shop.setShopDesc("test3");
		shop.setShopAddr("test3");
		shop.setPhone("test3");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File file = new File("/Users/tangli/Documents/xiaohuangren.jpg");
		InputStream is = new FileInputStream(file);
		ShopExecution shopExecution = shopService.addShop(shop,is, file.getName());
		assertEquals(ShopStateEnum.CHECK.getState(), shopExecution.getState());

	}


	public static void main(String[] args) {
//		Calendar calendar = Calendar.getInstance();//此时打印它获取的是系统当前时间
//		System.out.println(calendar.toString());
//		calendar.add(Calendar.DATE, -7);    //得到前一天
//		System.out.println(calendar.toString());
		Date date=new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(calendar.DATE, -7);
		date = calendar.getTime();
		SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
		String dateString = format.format(date);
		System.out.println(dateString);
	}
}