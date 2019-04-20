package com.tl.o2o.service.impl;

import com.tl.o2o.dao.ProductDao;
import com.tl.o2o.dao.ProductImgDao;
import com.tl.o2o.dto.ImageHolder;
import com.tl.o2o.dto.ProductExecution;
import com.tl.o2o.entity.Product;
import com.tl.o2o.entity.ProductImg;
import com.tl.o2o.enums.ProductEnum;
import com.tl.o2o.exception.CommonOperationException;
import com.tl.o2o.service.ProductService;
import com.tl.o2o.util.FileUtil;
import com.tl.o2o.util.ImageUtil;
import com.tl.o2o.util.PageCalculator;
import com.tl.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductImgDao productImgDao;

    @Override
    public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
        List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
        int count = productDao.queryProductCount(productCondition);
        ProductExecution pe = new ProductExecution();
        pe.setProductList(productList);
        pe.setCount(count);
        return pe;
    }

    @Override
    public Product queryProductByProductId(Long productId) {
        return productDao.queryProductByProductId(productId);
    }

    @Override
    @Transactional
    public ProductExecution addProduct(Product product, ImageHolder thumbnails, List<ImageHolder> productImgs) throws CommonOperationException {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            //默认属性
            product.setCreateTime(new Date());
            product.setLastEditTime(new Date());
            product.setEnableStatus(1);

            //1.处理缩略图，获取它的相对路径并赋值给product
            if (thumbnails != null) {
                addThumbnail(product, thumbnails);
            }
            //2.往tb_product插入商品信息，获取相对productId
            try {
                int effectedNum = productDao.insertProduct(product);
                if (effectedNum <= 0) {
                    throw new CommonOperationException("创建商品失败");
                }
            } catch (Exception e) {
                throw new CommonOperationException("创建商品失败" + e.toString());
            }

            //3.根据productId批量处理商品详情图
            if (productImgs != null && productImgs.size() > 0) {
                //4.将商品详情图列表批量插入到tb_product_img中
                addProductImgList(product, productImgs);
            }
            return new ProductExecution(ProductEnum.SUCCESS, product);
        } else {
            return new ProductExecution(ProductEnum.EMPTY);
        }
    }

    @Override
    @Transactional
    public ProductExecution modifyProduct(Product product, ImageHolder thumbnails, List<ImageHolder> productImgs) throws CommonOperationException {
        if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
            product.setLastEditTime(new Date());
            if (thumbnails != null) {
                Product tempProduct = productDao.queryProductByProductId(product.getProductId());
                if (tempProduct.getImgAddr() != null) {
                    FileUtil.deleteFile(tempProduct.getImgAddr());
                }

                addThumbnail(product, thumbnails);
            }
            if (productImgs != null && productImgs.size() > 0) {
                deleteProductImgs(product.getProductId());
                addProductImgList(product, productImgs);
            }
            try {
                int effectedNum = productDao.updateProduct(product);
                if (effectedNum <= 0) {
                    throw new CommonOperationException("更新商品信息失败");
                }
                return new ProductExecution(ProductEnum.SUCCESS, product);
            } catch (Exception e) {
                throw new CommonOperationException("更新商品信息失败:" + e.toString());
            }
        } else {
            return new ProductExecution(ProductEnum.EMPTY);
        }
    }

    /**
     * 批量添加图片
     *
     * @param product
     * @param productImgs
     */
    public void addProductImgList(Product product, List<ImageHolder> productImgs) {
        String desc = PathUtil.getShopImagePath(product.getShop().getShopId());
        List<ProductImg> productImgList = new ArrayList<>();

        for (ImageHolder imageHolder : productImgs) {
            String imgAddr = ImageUtil.generateNormalImg(imageHolder, desc);
            ProductImg productImg = new ProductImg();
            productImg.setImgAddr(imgAddr);
            productImg.setProductId(product.getProductId());
            productImg.setCreateTime(new Date());
            productImgList.add((productImg));
        }

        if (productImgList.size() > 0) {
            try {
                int effectedNum = productImgDao.batchInsertProductImg(productImgList);
                if (effectedNum <= 0) {
                    throw new CommonOperationException("创建商品详情图失败");
                }
            } catch (Exception e) {
                throw new CommonOperationException("创建商品详情图失败" + e.toString());
            }
        }
    }

    /**
     * 添加缩略图
     *
     * @param product
     * @param thumbnail
     */
    public void addThumbnail(Product product, ImageHolder thumbnail) {
        String desc = PathUtil.getShopImagePath(product.getShop().getShopId());
        String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, desc);
        product.setImgAddr(thumbnailAddr);
    }

    private void deleteProductImgs(long productId) {
        List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
        for (ProductImg productImg : productImgList) {
            FileUtil.deleteFile(productImg.getImgAddr());
        }
        productImgDao.deleteProductImg(productId);
    }
}
