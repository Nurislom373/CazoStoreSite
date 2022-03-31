package com.st2emx.online_store.controller.admin.color;

import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.dto.color.ColorCreateDto;
import com.st2emx.online_store.dto.color.ColorDto;
import com.st2emx.online_store.service.admin.color.ColorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/color/*")
public class ColorController extends AbstractController<ColorService> {
    public ColorController(ColorService service) {
        super(service);
    }

    @RequestMapping(value = "list")
    public ModelAndView listPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("colors", service.getAllColors());
        modelAndView.setViewName("admin/color/ColorList");
        return modelAndView;
    }

    @RequestMapping(value = "create", method = RequestMethod.GET)
    public String createPage() {
        return "admin/color/ColorCreate";
    }

    @RequestMapping(value = "create", method = RequestMethod.POST)
    public String create(@ModelAttribute ColorCreateDto dto) {
        service.create(dto);
        return "redirect:/admin/home";
    }

    @RequestMapping(value = "delete/{id}",method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/color/list";
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.GET)
    public ModelAndView updatePage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView();
        ColorDto update = service.get(id);
        modelAndView.addObject("color", update);
        modelAndView.setViewName("admin/color/ColorUpdate");
        return modelAndView;
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable Long id, @ModelAttribute ColorCreateDto dto) {
        service.update(id, dto);
        return "redirect:/admin/home";
    }
}
