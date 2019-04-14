package com.tl.o2o.service;

import com.tl.o2o.dto.ImageHolder;
import com.tl.o2o.dto.ProductExecution;
import com.tl.o2o.entity.Product;
import com.tl.o2o.exception.CommonOperationException;

import java.io.InputStream;
import java.util.List;

public interface ProductService {

    /**
     * 添加商品信息和图片处理
     * @param product
     * 缩略图
     * @param thumbnails
     * 详情图
     * @param productImgs
     * @return
     * @throws CommonOperationException
     */
    ProductExecution addProduct(Product product, ImageHolder thumbnails, List<ImageHolder> productImgs) throws CommonOperationException;
}
