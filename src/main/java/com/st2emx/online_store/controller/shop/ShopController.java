package com.st2emx.online_store.controller.shop;

import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.dto.shop.FilterDto;
import com.st2emx.online_store.service.home.HomeService;
import com.st2emx.online_store.service.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/shop/*")
public class ShopController extends AbstractController<ProductService> {

    private final HomeService homeService;

    public ShopController(ProductService service, HomeService homeService) {
        super(service);
        this.homeService = homeService;
    }

    @RequestMapping(value = "/")
    public ModelAndView shopPage(@ModelAttribute FilterDto filterDto) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/shop");
        modelAndView.addObject("categories", homeService.getAllCategories());
        modelAndView.addObject("products", homeService.homeProcessing());
        return modelAndView;
    }


}
