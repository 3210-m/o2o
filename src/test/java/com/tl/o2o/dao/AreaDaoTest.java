package com.tl.o2o.dao;

import com.tl.o2o.BaseTest;
import com.tl.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * 测试Area
 *
 * @author tangli
 * @create 2018-11-03 下午3:57
 **/
public class AreaDaoTest extends BaseTest{
	@Autowired
	private AreaDao areaDao;

	@Test
	public void testQueryArea(){
		List<Area> list = areaDao.queryArea();
		assertEquals(2,list.size());
	}
}
