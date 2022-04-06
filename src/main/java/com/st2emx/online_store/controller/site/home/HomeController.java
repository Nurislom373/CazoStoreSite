package com.st2emx.online_store.controller.site.home;

import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.dto.file.FileDto;
import com.st2emx.online_store.service.site.authCart.AuthCartService;
import com.st2emx.online_store.service.site.home.HomeService;
import com.st2emx.online_store.utils.BaseUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("")
public class HomeController extends AbstractController<HomeService> {

    private final AuthCartService authCartService;

    public HomeController(HomeService service, AuthCartService authCartService) {
        super(service);
        this.authCartService = authCartService;
    }

    @RequestMapping(value = "/")
    public String indexPage(HttpServletRequest request) {
        return service.indexCheck(request.getCookies());
    }

    @RequestMapping(value = "/no_auth")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", service.homeProcessing());
        modelAndView.addObject("categories", service.getAllCategories());
        modelAndView.setViewName("home/index");
        return modelAndView;
    }

    @RequestMapping(value = "/auth")
    public ModelAndView authHomePage(@CookieValue("userId") Long userId, @CookieValue("token") String token) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", service.getUserById(userId));
        modelAndView.addObject("products", service.getProducts());
        modelAndView.addObject("CartCount", authCartService.getCards(userId, token).size());
        modelAndView.addObject("categories", service.getAllCategories());
        modelAndView.addObject("likeCount", service.getLike(userId));
        modelAndView.setViewName("home/auth_index");
        return modelAndView;
    }

    @RequestMapping(value = "/file")
    public String filePage() {
        return "product/input";
    }

    @RequestMapping(value = "/file", method = RequestMethod.POST)
    public String file(@RequestParam("file") MultipartFile file) {
        FileDto fileDto = BaseUtils.sendFileUpload("http://localhost:8080/api/v1/file/product/upload", file);
        System.out.println("fileDto = " + fileDto);
        return "product/input";
    }
}
