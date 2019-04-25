package com.tl.o2o.web.frontend;


import com.tl.o2o.dto.ProductExecution;
import com.tl.o2o.entity.Product;
import com.tl.o2o.entity.ProductCategory;
import com.tl.o2o.entity.Shop;
import com.tl.o2o.service.ProductCategoryService;
import com.tl.o2o.service.ProductService;
import com.tl.o2o.service.ShopService;
import com.tl.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/frontend",method = RequestMethod.GET)
public class ShopDetailController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ShopService shopService;

    @Autowired
    private ProductService productService;


    /**
     * 获取店铺信息和店铺中的productCategory信息
     *
     * @param request
     * @return
     */
    @RequestMapping("listshopdetailpageinfo")
    @ResponseBody
    public Map<String,Object> listShopDetailPageInfo(HttpServletRequest request){
        Map<String, Object> modelMap = new HashMap<>();

        //获取参数
        long shopId = HttpServletRequestUtil.getLong(request,"shopId");
        Shop shop = null;
        List<ProductCategory> productCategoryList = null;
        if (shopId!=-1L){
            //获取店铺信息
            shop = shopService.getByShopId(shopId);
            //获取商品信息
            productCategoryList = productCategoryService.getProductCategory(shopId);


            modelMap.put("shop",shop);
            modelMap.put("productCategoryList",productCategoryList);
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty shopId");
        }

        return modelMap;
    }


    /**
     * 根据条件分页获取商品信息
     *
     * @param request
     * @return
     */
    @RequestMapping("listproductbyshop")
    @ResponseBody
    public Map<String,Object> listProductByShop(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<>();

        //获取前端参数
        int pageIndex = HttpServletRequestUtil.getInt(request,"pageIndex");
        int pageSize = HttpServletRequestUtil.getInt(request,"pageSize");
        long shopId = HttpServletRequestUtil.getLong(request,"shopId");
        if((pageIndex>-1)&&(pageSize>-1)&&(shopId>-1L)){
            long productCategoryId = HttpServletRequestUtil.getLong(request,"productCategoryId");
            String productName = HttpServletRequestUtil.getString(request,"productName");
            //组合参数
            Product productCondition = compactProductConditionSearch(shopId,productCategoryId,productName);

            ProductExecution pe = productService.getProductList(productCondition,pageIndex,pageSize);

            modelMap.put("productList",pe.getProductList());
            modelMap.put("count",pe.getCount());
            modelMap.put("success",true);
        }else {
            modelMap.put("success",false);
            modelMap.put("errMsg","empty pageIndex or pageSize or shopId");
        }

        return modelMap;
    }


    /**
     * 组合参数
     * @param shopId
     * @param productCategoryId
     * @param productName
     * @return
     */
    private Product compactProductConditionSearch(long shopId, long productCategoryId, String productName){
        Shop shop = new Shop();
        shop.setShopId(shopId);
        Product productCondition = new Product();
        productCondition.setShop(shop);
        if(productCategoryId!=-1L){
            //查询shopId下的商品列表
            ProductCategory productCategory = new ProductCategory();
            productCategory.setProductCategoryId(productCategoryId);
            productCondition.setProductCategory(productCategory);
        }
        if(productName!=null){
            //根据productName查询商品列表
            productCondition.setProductName(productName);
        }

        productCondition.setEnableStatus(1);
        return productCondition;

    }
}
