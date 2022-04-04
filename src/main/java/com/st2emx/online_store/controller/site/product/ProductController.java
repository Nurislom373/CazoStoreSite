package com.st2emx.online_store.controller.site.product;

import com.st2emx.online_store.config.session.SessionToken;
import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.dto.flash.FlashDto;
import com.st2emx.online_store.dto.product.ProductCommentCreateDto;
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
@RequestMapping("/product/*")
public class ProductController extends AbstractController<ProductService> {

    private final HomeService homeService;

    public ProductController(ProductService service, HomeService homeService) {
        super(service);
        this.homeService = homeService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ModelAndView getProduct(@PathVariable Long id, HttpServletRequest request, @CookieValue("userId") Long userId) {
        ModelAndView modelAndView = new ModelAndView();
        if (Objects.isNull(userId)) {
            modelAndView.setViewName("product/product-detail");
            modelAndView.addObject("product", service.getProductFull(id));
        } else {
            modelAndView.setViewName("product/auth_product-detail");
            modelAndView.addObject("product", service.getProductFull(id));
            modelAndView.addObject("user", homeService.getUserById(userId));
            modelAndView.addObject("likeCount", homeService.getLike());
        }
        modelAndView.addObject("error", new FlashDto());
        return modelAndView;
    }

    @RequestMapping(value = "/like/{id}")
    public String productLike(@PathVariable Long id) {
        service.productLike(id);
        return "redirect:/";
    }

    @RequestMapping(value = "/like/delete/{id}")
    public ModelAndView productLikeDelete(@PathVariable Long id, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        service.productLikeDelete(id);
        Optional<String> any = Arrays.stream(request.getCookies()).filter(cookie -> "userId".equals(cookie.getName())).map(Cookie::getValue).findAny();
        modelAndView.addObject("user", homeService.getUserById(Long.parseLong(any.get())));
        modelAndView.addObject("products", homeService.getAllProductLike());
        modelAndView.addObject("likeCount", homeService.getLike());
        modelAndView.setViewName("product/like-cart");
        return modelAndView;
    }

    @RequestMapping(value = "like")
    public ModelAndView productLikePage(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<String> any = Arrays.stream(request.getCookies()).filter(cookie -> "userId".equals(cookie.getName())).map(Cookie::getValue).findAny();
        modelAndView.addObject("user", homeService.getUserById(Long.parseLong(any.get())));
        modelAndView.addObject("products", homeService.getAllProductLike());
        modelAndView.addObject("likeCount", homeService.getLike());
        modelAndView.setViewName("product/like-cart");
        return modelAndView;
    }

    @RequestMapping(value = "comment/{id}", method = RequestMethod.POST)
    public ModelAndView productComment(@PathVariable Long id, @ModelAttribute ProductCommentCreateDto productCommentCreateDto) {
        ModelAndView modelAndView = new ModelAndView();
        if (Objects.isNull(SessionToken.getSession())) {
            FlashDto dto = new FlashDto();
            dto.setMessage("Please bro login!");
            dto.setMain(true);
            modelAndView.addObject("error", dto);
            modelAndView.addObject("product", service.getProductFull(id));
            modelAndView.setViewName("product/product-detail");
            return modelAndView;
        }
        service.productCommentCreate(id, productCommentCreateDto);
        modelAndView.setViewName("redirect:/product/" + id);
        return modelAndView;
    }


}
