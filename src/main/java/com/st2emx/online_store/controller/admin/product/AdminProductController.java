package com.st2emx.online_store.controller.admin.product;

import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.service.admin.product.AdminProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/product/*")
public class AdminProductController extends AbstractController<AdminProductService> {

    public AdminProductController(AdminProductService service) {
        super(service);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView listPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", service.getAll());
        modelAndView.setViewName("admin/product/ProductsList");
        return modelAndView;
    }
}
