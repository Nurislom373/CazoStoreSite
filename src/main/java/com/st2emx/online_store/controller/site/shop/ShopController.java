package com.st2emx.online_store.controller.site.shop;

import com.st2emx.online_store.config.session.SessionToken;
import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.dto.shop.FilterDto;
import com.st2emx.online_store.service.site.home.HomeService;
import com.st2emx.online_store.service.site.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

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
    public ModelAndView shopPage(@ModelAttribute FilterDto filterDto, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (Objects.isNull(SessionToken.getSession())) {
            modelAndView.setViewName("product/shop");
            modelAndView.addObject("categories", homeService.getAllCategories());
            modelAndView.addObject("products", homeService.homeProcessing());
            modelAndView.addObject("clrs", homeService.getAllColor());
        } else {
            modelAndView.setViewName("product/auth_shop");
            Optional<String> any = Arrays.stream(request.getCookies()).filter(cookie -> "userId".equals(cookie.getName())).map(Cookie::getValue).findAny();
            modelAndView.addObject("categories", homeService.getAllCategories());
            modelAndView.addObject("products", homeService.homeProcessing());
            modelAndView.addObject("clrs", homeService.getAllColor());
            modelAndView.addObject("user", homeService.getUserById(Long.parseLong(any.get())));
            modelAndView.addObject("likeCount", homeService.getLike());
        }
        return modelAndView;
    }

    @RequestMapping(value = "filter")
    public ModelAndView filter(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        if (Objects.isNull(SessionToken.getSession())) {
            modelAndView.setViewName("product/shop");
            modelAndView.addObject("categories", homeService.getAllCategories());
            modelAndView.addObject("products", service.getProductByFilterAndPagination(new FilterDto(filterDto.getSortById(), filterDto.getPriceId(), filterDto.getColorId()), page));
            modelAndView.addObject("clrs", homeService.getAllColor());
        } else {
            modelAndView.setViewName("product/auth_shop");
            Optional<String> any = Arrays.stream(request.getCookies()).filter(cookie -> "userId".equals(cookie.getName())).map(Cookie::getValue).findAny();
            modelAndView.addObject("user", homeService.getUserById(Long.parseLong(any.get())));
            modelAndView.addObject("likeCount", homeService.getLike());
            modelAndView.addObject("categories", homeService.getAllCategories());
            modelAndView.addObject("products", service.getProductByFilterAndPagination(new FilterDto(filterDto.getSortById(), filterDto.getPriceId(), filterDto.getColorId()), page));
            modelAndView.addObject("clrs", homeService.getAllColor());
        }
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
    public ModelAndView shopPageBySearch(@RequestParam String word, HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView();
        if (Objects.isNull(SessionToken.getSession())) {
            modelAndView.setViewName("product/shop");
            modelAndView.addObject("categories", homeService.getAllCategories());
            modelAndView.addObject("products", service. getProductBySearchAndPagination(word,page));
        } else {
            modelAndView.setViewName("product/auth_shop");
            Optional<String> any = Arrays.stream(request.getCookies()).filter(cookie -> "userId".equals(cookie.getName())).map(Cookie::getValue).findAny();
            modelAndView.addObject("categories", homeService.getAllCategories());
            modelAndView.addObject("products", service. getProductBySearchAndPagination(word,page));
            modelAndView.addObject("user", homeService.getUserById(Long.parseLong(any.get())));
            modelAndView.addObject("likeCount", homeService.getLike());
        }
        return modelAndView;
    }




}
