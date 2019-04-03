package com.tl.o2o.service;

import com.tl.o2o.dto.ShopExecution;
import com.tl.o2o.entity.Shop;
import com.tl.o2o.exception.ShopOperationException;

import java.io.InputStream;

/**
 * @author tangli
 * @create 2018-11-06 下午12:16
 **/
public interface ShopService {
    /**
     * 根据shopCondition分页的返回店铺信息列表
     * @param shopCondition
     * @param pageSize
     * @param pageIndex
     * @return
     */
    ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

    /**
     * 获取店铺信息byId
     * @param shopId
     * @return
     */
    Shop getByShopId(long shopId);

    /**
     * 修改店铺信息，图片
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     * @throws ShopOperationException
     */
    ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

    /**
     * 添加店铺信息，图片处理
     * @param shop
     * @param shopImgInputStream
     * @param fileName
     * @return
     * @throws ShopOperationException
     */
	ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

}
