package com.st2emx.online_store.controller.shop;

import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.dto.shop.FilterDto;
import com.st2emx.online_store.service.home.HomeService;
import com.st2emx.online_store.service.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/shop/*")
public class ShopController extends AbstractController<ProductService> {

    private final HomeService homeService;
    public static FilterDto filterDto = new FilterDto(0L, 0L, 0L);
    public static Integer page = 0;

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
        modelAndView.addObject("clrs", homeService.getAllColor());
        return modelAndView;
    }

    @RequestMapping(value = "filter")
    public ModelAndView filter() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product/shop");
        modelAndView.addObject("categories", homeService.getAllCategories());
        modelAndView.addObject("products", service.getProductByFilterAndPagination(new FilterDto(filterDto.getSortById(), filterDto.getPriceId(), filterDto.getColorId()), page));
        modelAndView.addObject("clrs", homeService.getAllColor());
        return modelAndView;
    }

    @RequestMapping(value = "setSortBy/{id}")
    public String setSortBy(@PathVariable Long id) {
        filterDto.setSortById(id);
        return "redirect:/shop/filter";
    }

    @RequestMapping(value = "priceId/{id}")
    public String setPrice(@PathVariable Long id) {
        filterDto.setPriceId(id);
        return "redirect:/shop/filter";
    }

    @RequestMapping(value = "colorId/{id}")
    public String setColor(@PathVariable Long id) {
        filterDto.setColorId(id);
        return "redirect:/shop/filter";
    }

    @PostMapping(value = "search")
    public ModelAndView shopPageBySearch(@RequestParam String word){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("product/shop");
        modelAndView.addObject("categories", homeService.getAllCategories());
        modelAndView.addObject("products", service. getProductBySearchAndPagination(word,page));
        return modelAndView;
    }




}
