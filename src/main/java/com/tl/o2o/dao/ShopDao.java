package com.tl.o2o.dao;

import com.tl.o2o.entity.Shop;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author tangli
 * @create 2018-11-04 下午9:02
 **/
public interface ShopDao {
	/**
	 * 查询店铺信息，可根据几个条件组合
	 */

	/**
	 *
	 * @param shopCondition
	 * @param rowIndex 从那行开始取
	 * @param pageSize 取几条
	 * @return
	 */
	List<Shop> queryShopList(@Param("shopCondition")Shop shopCondition,
							 @Param("rowIndex") int rowIndex,
							 @Param("pageSize") int pageSize);

	/**
	 * 查询的总条数
	 * @param shopCondition
	 * @return
	 */
	int queryShopCount(@Param("shopCondition")Shop shopCondition);
	/**
	 *
	 * 根据shopId查询店铺
	 *
	 * @param shopId
	 * @return
	 */
	Shop queryByShopId(long shopId);
	/**
	 * 新增店铺
	 *
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);

	/**
	 * 更新店铺信息
	 *
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
}
