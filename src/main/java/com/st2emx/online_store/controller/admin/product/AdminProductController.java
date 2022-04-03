package com.st2emx.online_store.controller.admin.product;

import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.dto.product.ProductCreateDto;
import com.st2emx.online_store.service.admin.product.AdminProductService;
import com.st2emx.online_store.service.site.home.HomeService;
import com.st2emx.online_store.service.site.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/product/*")
public class AdminProductController extends AbstractController<AdminProductService> {

    private final HomeService homeService;
    private final ProductService productService;

    public AdminProductController(AdminProductService service, HomeService homeService, ProductService productService) {
        super(service);
        this.homeService = homeService;
        this.productService = productService;
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ModelAndView listPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("products", service.getAll());
        modelAndView.setViewName("admin/product/ProductsList");
        return modelAndView;
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public ModelAndView createPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", homeService.getAllCategories());
        modelAndView.setViewName("admin/product/ProductCreate");
        return modelAndView;
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@ModelAttribute ProductCreateDto createDto) {
        service.create(createDto);
        return "redirect:/admin/product/list";
    }

    @RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
    public ModelAndView detailPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("product", productService.getProductFull(id));
        modelAndView.setViewName("admin/product/ProductDetail");
        return modelAndView;
    }
}
