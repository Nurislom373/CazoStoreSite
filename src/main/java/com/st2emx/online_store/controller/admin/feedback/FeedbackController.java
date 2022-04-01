package com.st2emx.online_store.controller.admin.feedback;

import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.service.admin.feedback.FeedbackService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin/feedback/*")
public class FeedbackController extends AbstractController<FeedbackService> {

    public FeedbackController(FeedbackService service) {
        super(service);
    }

    @RequestMapping(value = "list")
    public ModelAndView listPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("feedbacks", service.getAllFeedbacks());
        modelAndView.setViewName("admin/feedback/FeedbackList");
        return modelAndView;
    }

    @RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/admin/feedback/list";
    }

}
