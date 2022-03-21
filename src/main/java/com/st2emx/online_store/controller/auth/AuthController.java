package com.st2emx.online_store.controller.auth;

import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.dto.auth.LoginDto;
import com.st2emx.online_store.service.auth.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/auth/*")
public class AuthController extends AbstractController<AuthService> {

    public AuthController(AuthService service) {
        super(service);
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "auth/index";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@ModelAttribute LoginDto loginDto) {
        service.login(loginDto);
        return "redirect:/";
    }
}
