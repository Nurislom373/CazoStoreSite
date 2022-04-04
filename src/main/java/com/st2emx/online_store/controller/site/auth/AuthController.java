package com.st2emx.online_store.controller.site.auth;

import com.st2emx.online_store.config.session.SessionToken;
import com.st2emx.online_store.controller.AbstractController;
import com.st2emx.online_store.dto.auth.*;
import com.st2emx.online_store.dto.token.TokenDto;
import com.st2emx.online_store.service.site.auth.AuthService;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

import static com.st2emx.online_store.utils.BaseUtils.createCookie;

@Controller
@RequestMapping("/auth/*")
public class AuthController extends AbstractController<AuthService> {

    public AuthController(AuthService service) {
        super(service);
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "auth/new_login";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String registerPage() {
        return "auth/new_register";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@ModelAttribute LoginDto loginDto, HttpServletResponse response) {
        TokenDto login = service.login(loginDto);
        response.addCookie(createCookie("userId", String.valueOf(login.getUserId())));
        response.addCookie(createCookie("token", login.getAccessToken()));
        return "redirect:/auth";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String register(@ModelAttribute RegisterDto registerDto) {
        service.register(registerDto);
        return "redirect:/auth/login";
    }

    @RequestMapping(value = "profile", method = RequestMethod.GET)
    public ModelAndView profilePage() {
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("auth/profile");
        UserDto userDto = service.getUserDetails(SessionToken.getSession().getUserId());
        modelAndView.addObject("user",userDto);
        modelAndView.addObject("userId",SessionToken.getSession().getUserId());
        return modelAndView;
    }
    @RequestMapping(value = "profile/{id}", method = RequestMethod.POST)
    public String profileUpdate(@PathVariable Integer id, @ModelAttribute UserUpdateDto userUpdateDto) throws JSONException {
        service.updateProfile(id,userUpdateDto);
        return "redirect:/auth";
    }

    @RequestMapping(value = "profileSettings/{id}", method = RequestMethod.POST)
    public String profileSettingsUpdate(@PathVariable Integer id, @ModelAttribute UserSettingsDto userSettingsDto) throws JSONException {
        service.updateUserSettings(id,userSettingsDto);
        return "redirect:/auth";
    }

    @RequestMapping(value = "changePassword/{id}", method = RequestMethod.POST)
    public String profileChangePassword(@PathVariable Integer id, @ModelAttribute ChangePasswordDto changePasswordDto) throws JSONException {
        service.changePassword(id,changePasswordDto);
        return "redirect:/auth";
    }
}
