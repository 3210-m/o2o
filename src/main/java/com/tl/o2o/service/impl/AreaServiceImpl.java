package com.tl.o2o.service.impl;

import com.tl.o2o.dao.AreaDao;
import com.tl.o2o.entity.Area;
import com.tl.o2o.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author tangli
 * @create 2018-11-03 下午7:10
 **/
@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao areaDao;


	@Override
	public List<Area> getAreaList() {
		return areaDao.queryArea();
	}
}
