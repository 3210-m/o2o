package com.tl.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/frontend", method = {RequestMethod.GET})
public class FrontendController {
    @RequestMapping("/index")
    public String index() {
        return "frontend/index";
    }

    @RequestMapping("/shopdetail")
    public String shopdetail() {
        return "frontend/shopdetail";
    }

    @RequestMapping("/productdetail")
    public String productdetail() {
        return "frontend/productdetail";
    }

    @RequestMapping("/shoplist")
    public String shoplist() {
        return "frontend/shoplist";
    }
}
