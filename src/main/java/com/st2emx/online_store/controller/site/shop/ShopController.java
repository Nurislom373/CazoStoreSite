package com.st2emx.online_store.controller.site.shop;

import com.st2emx.online_store.config.session.SessionToken;
import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.dto.shop.FilterDto;
import com.st2emx.online_store.service.site.authCart.AuthCartService;
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
    private final AuthCartService cartService;
    public static FilterDto filterDto = new FilterDto(0L, 0L, 0L);
    public static Integer page = 0;

    public ShopController(ProductService service, HomeService homeService, AuthCartService cartService) {
        super(service);
        this.homeService = homeService;
        this.cartService = cartService;
    }

    @RequestMapping(value = "/")
    public ModelAndView shopPage(@ModelAttribute FilterDto filterDto, @CookieValue("userId") Long userId, @CookieValue("token") String token) {
        ModelAndView modelAndView = new ModelAndView();
        if (Objects.isNull(userId)) {
            modelAndView.setViewName("product/shop");
            modelAndView.addObject("categories", homeService.getAllCategories());
            modelAndView.addObject("products", homeService.homeProcessing());
            modelAndView.addObject("clrs", homeService.getAllColor());
        } else {
            modelAndView.setViewName("product/auth_shop");
            modelAndView.addObject("categories", homeService.getAllCategories());
            modelAndView.addObject("products", homeService.homeProcessing());
            modelAndView.addObject("clrs", homeService.getAllColor());
            modelAndView.addObject("user", homeService.getUserById(userId));
            modelAndView.addObject("CartCount", cartService.getCards(userId, token).size());
            modelAndView.addObject("likeCount", homeService.getLike(userId));
        }
        return modelAndView;
    }

    @RequestMapping(value = "filter")
    public ModelAndView filter(@CookieValue("userId") Long userId, @CookieValue("token") String token) {
        ModelAndView modelAndView = new ModelAndView();
        if (Objects.isNull(userId)) {
            modelAndView.setViewName("product/shop");
            modelAndView.addObject("categories", homeService.getAllCategories());
            modelAndView.addObject("products", service.getProductByFilterAndPagination(new FilterDto(filterDto.getSortById(), filterDto.getPriceId(), filterDto.getColorId()), page));
            modelAndView.addObject("clrs", homeService.getAllColor());
        } else {
            modelAndView.setViewName("product/auth_shop");
            modelAndView.addObject("user", homeService.getUserById(userId));
            modelAndView.addObject("likeCount", homeService.getLike(userId));
            modelAndView.addObject("categories", homeService.getAllCategories());
            modelAndView.addObject("CartCount", cartService.getCards(userId, token).size());
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
    public ModelAndView shopPageBySearch(@RequestParam String word, @CookieValue("userId") Long userId, @CookieValue("token") String token){
        ModelAndView modelAndView=new ModelAndView();
        if (Objects.isNull(userId)) {
            modelAndView.setViewName("product/shop");
            modelAndView.addObject("categories", homeService.getAllCategories());
            modelAndView.addObject("products", service. getProductBySearchAndPagination(word,page));
        } else {
            modelAndView.setViewName("product/auth_shop");
            modelAndView.addObject("categories", homeService.getAllCategories());
            modelAndView.addObject("products", service. getProductBySearchAndPagination(word,page));
            modelAndView.addObject("user", homeService.getUserById(userId));
            modelAndView.addObject("likeCount", homeService.getLike(userId));
            modelAndView.addObject("CartCount", cartService.getCards(userId, token).size());
        }
        return modelAndView;
    }




}
