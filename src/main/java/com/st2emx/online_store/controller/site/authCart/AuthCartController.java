package com.st2emx.online_store.controller.site.authCart;

import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.dto.cart.CartCreateDto;
import com.st2emx.online_store.dto.cart.CartUpdateDto;
import com.st2emx.online_store.service.site.authCart.AuthCartService;
import com.st2emx.online_store.service.site.home.HomeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/cart/*")
public class AuthCartController extends AbstractController<AuthCartService> {

    private final HomeService homeService;

    public AuthCartController(AuthCartService service, HomeService homeService) {
        super(service);
        this.homeService = homeService;
    }

    @RequestMapping(value = "create/{id}", method = RequestMethod.POST)
    public String create(@ModelAttribute CartCreateDto dto, @PathVariable Long id) {
        service.create(dto,id);
        return "redirect:/product/" + id;
    }

    @RequestMapping(value = "shoping_cart",method = RequestMethod.GET)
    public ModelAndView getAll(@CookieValue("userId") Long userId) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("carts",service.getCards());
        modelAndView.addObject("CartCount", service.getCards().size());
        modelAndView.addObject("user", homeService.getUserById(userId));
        modelAndView.addObject("likeCount", homeService.getLike());
        modelAndView.addObject("sumCartPrice", service.sumCartPrice());
        modelAndView.setViewName("product/shoping_cart");
        return modelAndView;
    }

    @RequestMapping(value = "shoping_cart_update")
    public ModelAndView cartUpdate(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject( "carts",service.getCards());
        modelAndView.addObject("sumCartPrice", service.sumCartPrice());
        modelAndView.setViewName("product/shoping_cart_update");
        return modelAndView;
    }

    @RequestMapping(value = "shopping_cart_update",method = RequestMethod.POST)
    public ModelAndView shoppingCartUpdate(@ModelAttribute CartUpdateDto dto){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject( "carts",service.getCards());
        modelAndView.addObject("sumCartPrice", service.sumCartPrice());
        modelAndView.addObject("count",service.updateCart(dto));
        return cartUpdate();
    }



}
