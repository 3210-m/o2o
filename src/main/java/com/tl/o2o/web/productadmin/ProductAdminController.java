package com.tl.o2o.web.productadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/productcategoryadmin", method = {RequestMethod.GET})
public class ProductAdminController {
    @RequestMapping("/productcategorymanage")
    public String productCategoryManage() {

        return "product/productcategorymanagement";
    }

    @RequestMapping("/productcategoryoperation")
    public String productCategoryOperation() {
        return "product/productcategoryoperation";
    }
}
