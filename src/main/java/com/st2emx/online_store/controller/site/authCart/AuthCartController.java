package com.st2emx.online_store.controller.site.authCart;

import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.dto.cart.CartCreateDto;
import com.st2emx.online_store.service.site.authCart.AuthCartService;
import com.st2emx.online_store.service.site.home.HomeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

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

    @RequestMapping(value = "carts",method = RequestMethod.GET)
    public ModelAndView getAll(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<String> any = Arrays.stream(request.getCookies()).filter(cookie -> "userId".equals(cookie.getName())).map(Cookie::getValue).findAny();
        modelAndView.addObject("carts",service.getCards());
        modelAndView.addObject("user", homeService.getUserById(Long.parseLong(any.get())));
        modelAndView.addObject("likeCount", homeService.getLike());
        modelAndView.setViewName("product/your-cart");
        return modelAndView;
    }
}
