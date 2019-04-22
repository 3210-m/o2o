package com.tl.o2o.web.frontend;


import com.tl.o2o.entity.HeadLine;
import com.tl.o2o.entity.ShopCategory;
import com.tl.o2o.service.HeadLineService;
import com.tl.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 响应主页的请求
 * @author tangli
 * @create 2019-4-08 下午14:52
 **/

@Controller
@RequestMapping("/frontend")
public class MainPageController {
    @Autowired
    ShopCategoryService shopCategoryService;

    @Autowired
    HeadLineService headLineService;


    @RequestMapping(value = "/listmainpageinfo",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> listMainPageInfo(){
        Map<String,Object> modelMap = new HashMap<>();

        //获取一级列表
        List<ShopCategory> shopCategoryList = new ArrayList<>();
        try{
            shopCategoryList = shopCategoryService.getShopCategoryList(null);
            modelMap.put("shopCategoryList",shopCategoryList);
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("errorMsg", e.getMessage());
            return modelMap;
        }

        //获取头条列表
        List<HeadLine> headLineList = new ArrayList<>();
        try{
            HeadLine headLineCondition = new HeadLine();
            headLineCondition.setEnableStatus(1);
            headLineList = headLineService.getHeadLineList(headLineCondition);
            modelMap.put("headLineList",headLineList);
        }catch (Exception e){
            modelMap.put("success", false);
            modelMap.put("errorMsg", e.getMessage());
            return modelMap;
        }
        modelMap.put("success",true);
        return modelMap;
    }

}
