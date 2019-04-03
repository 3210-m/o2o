package com.tl.o2o.service;

import com.tl.o2o.BaseTest;
import com.tl.o2o.entity.Area;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author tangli
 * @create 2018-11-03 下午7:18
 **/
public class AreaServiceTest extends BaseTest {
	@Autowired
	private AreaService areaService;

	@Test
	public void testGetAreaList(){
		List<Area> areaList = areaService.getAreaList();
		assertEquals("天津",areaList.get(0).getAreaName());
	}
}
