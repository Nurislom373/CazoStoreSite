package com.st2emx.online_store.controller.admin.size;

import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.dto.size.SizeCreateDto;
import com.st2emx.online_store.dto.size.SizeDto;
import com.st2emx.online_store.service.admin.size.SizeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/size/*")
public class SizeController extends AbstractController<SizeService> {

    public SizeController(SizeService service) {
        super(service);
    }

    @RequestMapping(value = "list")
    public ModelAndView listPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("sizes", service.getAllSizes());
        modelAndView.setViewName("admin/size/SizeList");
        return modelAndView;
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createPage() {
        return "admin/size/SizeCreate";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@ModelAttribute SizeCreateDto dto) {
        service.create(dto);
        return "redirect:/admin/home";
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/size/list";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public ModelAndView updatePage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        SizeDto update = service.get(id);
        modelAndView.addObject("size", update);
        modelAndView.setViewName("admin/size/SizeUpdate");
        return modelAndView;
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable Long id, @ModelAttribute SizeCreateDto dto) {
        service.update(id, dto);
        return "redirect:/size/list";
    }
}
