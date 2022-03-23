package com.st2emx.online_store.controller.product;

import com.st2emx.online_store.config.session.SessionToken;
import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.dto.flash.FlashDto;
import com.st2emx.online_store.dto.product.ProductCommentCreateDto;
import com.st2emx.online_store.service.home.HomeService;
import com.st2emx.online_store.service.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/product/*")
public class ProductController extends AbstractController<ProductService> {

    private final HomeService homeService;

    public ProductController(ProductService service, HomeService homeService) {
        super(service);
        this.homeService = homeService;
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ModelAndView getProduct(@PathVariable Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        if (Objects.isNull(SessionToken.getSession())) {
            modelAndView.setViewName("product/product-detail");
            modelAndView.addObject("product", service.getProductFull(id));
        } else {
            modelAndView.setViewName("product/auth_product-detail");
            modelAndView.addObject("product", service.getProductFull(id));
            modelAndView.addObject("user", homeService.getUserById());
            modelAndView.addObject("likeCount", homeService.getLike());
        }
        model.addAttribute("error", new FlashDto(false, "boom"));
        return modelAndView;
    }

    @RequestMapping(value = "/like/{id}")
    public String productLike(@PathVariable Long id) {
        service.productLike(id);
        return "redirect:/";
    }

    @RequestMapping(value = "like")
    public ModelAndView productLikePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", homeService.getUserById());
        modelAndView.addObject("products", homeService.getAllProductLike());
        modelAndView.setViewName("product/like-cart");
        return modelAndView;
    }

    @RequestMapping(value = "comment/{id}", method = RequestMethod.POST)
    public String productComment(@PathVariable Long id, @ModelAttribute ProductCommentCreateDto productCommentCreateDto, Model model) {
        if (Objects.isNull(SessionToken.getSession())) {
            FlashDto dto = new FlashDto();
            dto.setMain(true);
            dto.setMessage("Please login!");
            model.addAttribute("error", dto);
            return "redirect:/product/" + id;
        }
        service.productCommentCreate(id, productCommentCreateDto);
        return "redirect:/product/" + id;
    }
}
