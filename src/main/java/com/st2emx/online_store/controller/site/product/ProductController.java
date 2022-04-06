package com.st2emx.online_store.controller.site.product;

import com.st2emx.online_store.config.session.SessionToken;
import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.dto.flash.FlashDto;
import com.st2emx.online_store.dto.product.ProductCommentCreateDto;
import com.st2emx.online_store.service.site.authCart.AuthCartService;
import com.st2emx.online_store.service.site.home.HomeService;
import com.st2emx.online_store.service.site.product.ProductService;
import com.st2emx.online_store.utils.BaseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/product/*")
public class ProductController extends AbstractController<ProductService> {

    private final HomeService homeService;
    private final AuthCartService cartService;

    public ProductController(ProductService service, HomeService homeService, AuthCartService cartService) {
        super(service);
        this.homeService = homeService;
        this.cartService = cartService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ModelAndView getProduct(HttpServletRequest request, @PathVariable Long id) {
        Cookie[] cookies = request.getCookies();
        ModelAndView modelAndView = new ModelAndView();
        if (!BaseUtils.checkCookies(cookies)) {
            modelAndView.setViewName("product/product-detail");
            modelAndView.addObject("product", service.getProductFull(id));
        } else {
            Long userId = Long.parseLong(BaseUtils.getCookie(cookies, "userId"));
            String token = BaseUtils.getCookie(cookies, "token");
            modelAndView.setViewName("product/auth_product-detail");
            modelAndView.addObject("product", service.getProductFull(id));
            modelAndView.addObject("CartCount", cartService.getCards(userId, token).size());
            modelAndView.addObject("user", homeService.getUserById(userId));
            modelAndView.addObject("likeCount", homeService.getLike(userId));
        }
        modelAndView.addObject("error", new FlashDto());
        return modelAndView;
    }

    @RequestMapping(value = "/like/{id}")
    public String productLike(@PathVariable Long id, @CookieValue("userId") Long userId) {
        service.productLike(id, userId);
        return "redirect:/";
    }

    @RequestMapping(value = "/like/delete/{id}")
    public ModelAndView productLikeDelete(@PathVariable Long id, @CookieValue("userId") Long userId, @CookieValue("token") String token) {
        ModelAndView modelAndView = new ModelAndView();
        service.productLikeDelete(id, userId);
        modelAndView.addObject("user", homeService.getUserById(userId));
        modelAndView.addObject("CartCount", cartService.getCards(userId, token).size());
        modelAndView.addObject("products", homeService.getAllProductLike(userId));
        modelAndView.addObject("likeCount", homeService.getLike(userId));
        modelAndView.setViewName("product/like-cart");
        return modelAndView;
    }

    @RequestMapping(value = "like")
    public ModelAndView productLikePage(@CookieValue("userId") Long userId, @CookieValue("token") String token) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", homeService.getUserById(userId));
        modelAndView.addObject("products", homeService.getAllProductLike(userId));
        modelAndView.addObject("CartCount", cartService.getCards(userId, token).size());
        modelAndView.addObject("likeCount", homeService.getLike(userId));
        modelAndView.setViewName("product/like-cart");
        return modelAndView;
    }

    @RequestMapping(value = "comment/{id}", method = RequestMethod.POST)
    public ModelAndView productComment(@PathVariable Long id, @ModelAttribute ProductCommentCreateDto productCommentCreateDto, @CookieValue("userId") Long userId) {
        ModelAndView modelAndView = new ModelAndView();
        if (Objects.isNull(userId)) {
            FlashDto dto = new FlashDto();
            dto.setMessage("Please bro login!");
            dto.setMain(true);
            modelAndView.addObject("error", dto);
            modelAndView.addObject("product", service.getProductFull(id));
            modelAndView.setViewName("product/product-detail");
            return modelAndView;
        }
        service.productCommentCreate(id, productCommentCreateDto, userId);
        modelAndView.setViewName("redirect:/product/" + id);
        return modelAndView;
    }


}
