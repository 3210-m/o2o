package com.tl.o2o.dao;

import com.tl.o2o.entity.ProductImg;

import java.util.List;

public interface ProductImgDao {
    /**
     * 批量查询
     * @param productId
     * @return
     */
    List<ProductImg> queryProductImgList(long productId);

    /**
     * 批量添加
     * @param productImgList
     * @return
     */
    int batchInsertProductImg(List<ProductImg> productImgList);

    /**
     * 根据id删除
     * @param productId
     * @return
     */
    int deleteProductImg(long productId);
}
