package com.st2emx.online_store.controller.admin.auth;

import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.service.admin.auth.AuthAdminService;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/admin/*")
public class AuthAdminController extends AbstractController<AuthAdminService> {

    public AuthAdminController(AuthAdminService service) {
        super(service);
    }

    @RequestMapping("auth")
    public String authPage() {
        return "admin/auth/login";
    }

    @RequestMapping("home")
    public String homePage() {
        return "admin/dashboard/home";
    }
}
