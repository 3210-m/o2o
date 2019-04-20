package com.tl.o2o.web.productadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/productadmin", method = {RequestMethod.GET})
public class ProductAdminController {
    @RequestMapping("/productmanage")
    public String productCategoryManage() {
        return "product/productmanagement";
    }

    @RequestMapping("/productoperation")
    public String productCategoryOperation() {
        return "product/productoperation";
    }

//    @RequestMapping(value = "/productedit", method = RequestMethod.GET)
//    private String productEdit() {
//        return "product/productedit";
//    }
}
