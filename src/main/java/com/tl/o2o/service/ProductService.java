package com.tl.o2o.service;

import com.tl.o2o.dto.ImageHolder;
import com.tl.o2o.dto.ProductExecution;
import com.tl.o2o.entity.Product;
import com.tl.o2o.exception.CommonOperationException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

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

    /**
     * 获得商品并分页
     * @param productCondition
     * @param pageIndex
     * @param pageSize
     * @return
     */
    ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);

    /**
     * 根据id获得商品
     * @param productId
     * @return
     */
    Product queryProductByProductId(Long productId);

    /**
     * 修改商品信息
     * @param product
     * @param thumbnails
     * @param productImgs
     * @return
     * @throws CommonOperationException
     */
    ProductExecution modifyProduct(Product product, ImageHolder thumbnails, List<ImageHolder> productImgs) throws CommonOperationException;
}
