package com.tl.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author tangli
 * @create 2018-11-25 下午10:28
 **/
@Controller
@RequestMapping(value = "shopadmin", method = {RequestMethod.GET})
public class ShopAdminController {
    @RequestMapping("/shopoperation")
    public String shopOperation() {
        return "shop/shopoperation";
    }

    @RequestMapping("/shoplist")
    public String shopList() {
        return "shop/shoplist";
    }

    @RequestMapping("/shopmanagement")
    public String shopManagement() {
        return "shop/shopmanagement";
    }


}
