package com.tl.o2o.dao;

import com.tl.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tangli
 * @create 2018-11-25 下午10:38
 * desc 店铺类别
 **/
public interface ShopCategoryDao {
	List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);
}
