package com.st2emx.online_store.controller.home;

import com.st2emx.online_store.config.session.SessionToken;
import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.service.home.HomeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
@RequestMapping("")
public class HomeController extends AbstractController<HomeService> {

    public HomeController(HomeService service) {
        super(service);
    }

    @RequestMapping(value = "/")
    public ModelAndView homePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", service.homeProcessing());
        modelAndView.addObject("categories", service.getAllCategories());
        modelAndView.setViewName("home/index");
        return modelAndView;
    }

    @RequestMapping(value = "/auth")
    public ModelAndView authHomePage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("user", service.getUserById());
        modelAndView.addObject("products", service.homeProcessing());
        modelAndView.addObject("categories", service.getAllCategories());
        modelAndView.addObject("likeCount", service.getLike());
        modelAndView.setViewName("home/auth_index");
        return modelAndView;
    }
}
