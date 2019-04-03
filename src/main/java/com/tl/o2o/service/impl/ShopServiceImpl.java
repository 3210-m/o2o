package com.tl.o2o.service.impl;

import com.tl.o2o.dao.ShopDao;
import com.tl.o2o.dto.ShopExecution;
import com.tl.o2o.entity.Shop;
import com.tl.o2o.enums.ShopStateEnum;
import com.tl.o2o.exception.ShopOperationException;
import com.tl.o2o.service.ShopService;
import com.tl.o2o.util.ImageUtil;
import com.tl.o2o.util.PageCalculator;
import com.tl.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * @author tangli
 * @create 2018-11-06 下午12:19
 **/
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopDao shopDao;


    @Override
    @Transactional
    public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
        if (shop == null) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        }
        try {
            //增加店铺初始信息
            shop.setEnableStatus(0);
            shop.setCreateTime(new Date());
            shop.setLastEditTime(new Date());

            //增加店铺信息
            int effectedNum = shopDao.insertShop(shop);
            if (effectedNum <= 0) {
                /*当且仅当抛出ShopOperationException或继承ShopOperationException的，
                 *事务才会得以终止并回滚，否则不会。
                 */
                throw new ShopOperationException("创建店铺失败");
            } else {
                if (shopImgInputStream != null) {
                    //存储图片
                    try {
                        addShopImg(shop, shopImgInputStream, fileName);
                    } catch (Exception e) {
                        throw new ShopOperationException("addShopImg error:" + e.getMessage());
                    }
                    //更新店铺的图片地址
                    effectedNum = shopDao.updateShop(shop);
                    if (effectedNum <= 0) {
                        throw new ShopOperationException("更新店铺的图片地址失败");
                    }
                }
            }

        } catch (Exception e) {
            throw new ShopOperationException("addShop error:" + e.getMessage());
        }
        return new ShopExecution(ShopStateEnum.CHECK, shop);
    }


    private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
        //获取shop图片目录相对路径
        String dest = PathUtil.getShopImagePath(shop.getShopId());
        String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream, fileName, dest);
        shop.setShopImg(shopImgAddr);
    }


    @Override
    public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
        List<Shop> shopList = shopDao.queryShopList(shopCondition, rowIndex, pageSize);
        int count = shopDao.queryShopCount(shopCondition);
        ShopExecution se = new ShopExecution();
        if(shopList!=null){
            se.setShopList(shopList);
            se.setCount(count);
            se.setState(ShopStateEnum.SUCCESS.getState());
        }else {
            se.setState(ShopStateEnum.INNER_ERROR.getState());
        }

        return se;
    }

    @Override
    public Shop getByShopId(long shopId) {
        return shopDao.queryByShopId(shopId);
    }

    @Override
    public ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException {
        if (shop == null || shop.getShopId() == null && fileName.equals("")) {
            return new ShopExecution(ShopStateEnum.NULL_SHOP);
        } else {
            try {
                //1.判断是否需要处理图片
                if (shopImgInputStream != null) {
                    Shop tempShop = shopDao.queryByShopId(shop.getShopId());
                    if (tempShop.getShopImg() != null) {
                        ImageUtil.deleteFileOrPath(tempShop.getShopImg());
                    }
                    addShopImg(shop, shopImgInputStream, fileName);
                }
                //2.更新店铺信息
                shop.setLastEditTime(new Date());
                int effectedNum = shopDao.updateShop(shop);
                if (effectedNum <= 0) {
                    return new ShopExecution(ShopStateEnum.INNER_ERROR);
                } else {
                    shop = shopDao.queryByShopId(shop.getShopId());
                    return new ShopExecution(ShopStateEnum.SUCCESS, shop);
                }

            }catch (Exception e){
                throw new ShopOperationException("modifyShop error:"+e.getMessage());
            }
        }

    }
}
