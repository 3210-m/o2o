package com.tl.o2o.dao;

import com.tl.o2o.entity.Area;

import java.util.List;

/**
 * @author tangli
 * @create 2018-11-03 下午2:29
 **/
public interface AreaDao {
	/**
	 * 区域列表
	 * @return areaList
	 */
	List<Area> queryArea();
}
