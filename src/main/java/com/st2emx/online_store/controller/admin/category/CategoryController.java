package com.st2emx.online_store.controller.admin.category;

import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.dto.category.CategoryCreateDto;
import com.st2emx.online_store.dto.category.CategoryDto;
import com.st2emx.online_store.service.admin.category.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/category/*")
public class CategoryController extends AbstractController<CategoryService> {

    public CategoryController(CategoryService service) {
        super(service);
    }

    @RequestMapping(value = "list")
    public ModelAndView listPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("categories", service.getAllCategories());
        modelAndView.setViewName("admin/category/CategoryList");
        return modelAndView;
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createPage() {
        return "admin/category/CategoryCreate";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@ModelAttribute CategoryCreateDto dto) {
        service.create(dto);
        return "redirect:/admin/home";
    }

    @RequestMapping(value = "delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/category/list";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public ModelAndView updatePage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        CategoryDto update = service.get(id);
        modelAndView.addObject("category", update);
        modelAndView.setViewName("admin/category/CategoryUpdate");
        return modelAndView;
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable Long id, @ModelAttribute CategoryCreateDto dto) {
        service.update(id, dto);
        return "redirect:/admin/home";
    }
}
