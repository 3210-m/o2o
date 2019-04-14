package com.tl.o2o.web.productcategoryadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/productcategoryadmin", method = {RequestMethod.GET})
public class ProductCategoryAdminController {
    @RequestMapping("/productcategorymanage")
    public String productCategoryManage() {

        return "shop/productcategorymanage";
    }

    @RequestMapping("/productcategoryoperation")
    public String productCategoryOperation() {
        return "shop/productcategoryoperation";
    }
}
