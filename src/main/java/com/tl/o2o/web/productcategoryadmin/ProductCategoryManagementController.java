package com.tl.o2o.web.productcategoryadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tl.o2o.dto.ProductCategoryExecution;
import com.tl.o2o.entity.ProductCategory;
import com.tl.o2o.entity.Shop;
import com.tl.o2o.enums.ProductCategoryEnum;
import com.tl.o2o.exception.CommonOperationException;
import com.tl.o2o.service.ProductCategoryService;
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
@RequestMapping(value = "/productcategoryadmin")
public class ProductCategoryManagementController {

    @Autowired
    ProductCategoryService productCategoryService;

    @RequestMapping(value = "/productcategory",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> productCategory(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        //long shopId = HttpServletRequestUtil.getLong(request,"shopId");
        Object currentShopObj = request.getSession().getAttribute("currentShop");
        if(currentShopObj!=null){
            Shop shop = (Shop)currentShopObj;

            List<ProductCategory> productCategoryList = productCategoryService.getProductCategory(shop.getShopId());
            modelMap.put("productCategoryList",productCategoryList);
            modelMap.put("success",true);
        }else{
            modelMap.put("success",false);
        }

        return modelMap;
    }

    @RequestMapping(value = "/addproductcategory")
    @ResponseBody
    public Map<String,Object> addProductCategory(HttpServletRequest request){
        Map<String,Object> modelMap = new HashMap<>();
        Object currentShopObj = request.getSession().getAttribute("currentShop");
        if(currentShopObj!=null){
            //接受JS中formDate传过来的参数
            String productCategoryStr = HttpServletRequestUtil.getString(request,"productCategoryStr");
            ObjectMapper mapper = new ObjectMapper();
            ProductCategory productCategory = null;
            try {
                productCategory = mapper.readValue(productCategoryStr, ProductCategory.class);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errorMsg", e.getMessage());
                return modelMap;
            }

            //获取session中的shopId
            Shop shop = (Shop)currentShopObj;
            productCategory.setShopId(shop.getShopId());
            ProductCategoryExecution productCategoryExecution;

            try{
                productCategoryExecution = productCategoryService.addProductCategory(productCategory);
                if(productCategoryExecution.getState() == ProductCategoryEnum.SUCCESS.getState()){
                    modelMap.put("success",true);
                }else {
                    modelMap.put("success",false);
                    modelMap.put("errorMsg", productCategoryExecution.getStateInfo());
                }
            }catch (CommonOperationException e){
                modelMap.put("success", false);
                modelMap.put("errorMsg", ProductCategoryEnum.INSERT_ERROR+e.getMessage());
            }
            return modelMap;
        }else{
            modelMap.put("success",false);
            modelMap.put("errorMsg",ProductCategoryEnum.NULL_SHOPID);
            return modelMap;
        }

    }

    @RequestMapping(value = "/deleteproductcategory", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> deleteProductCategory(HttpServletRequest request,Long productCategoryId){
        Map<String,Object> modelMap = new HashMap<>();

        if(productCategoryId!=null&&productCategoryId>0){
            try{
                ProductCategoryExecution pe = productCategoryService.deleteProductCategory(productCategoryId);
                if (pe.getState() == ProductCategoryEnum.SUCCESS.getState()) {
                    modelMap.put("success", true);
                } else {
                    modelMap.put("success", false);
                    modelMap.put("errMsg", pe.getStateInfo());
                }
            }catch (CommonOperationException e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", e.toString());
                return modelMap;
            }catch (Exception e){
                modelMap.put("success",false);
                modelMap.put("errorMsg",e.getMessage());
                return modelMap;
            }
        }else {
            modelMap.put("success",false);
            modelMap.put("errorMsg",ProductCategoryEnum.NULL_PRODUCTCATEGORY);
        }
        return modelMap;
    }



}
