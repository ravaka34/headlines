package com.aina.headlines.controller;

import com.aina.headlines.exception.LoginNotFound;
import com.aina.headlines.model.Login;
import com.aina.headlines.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    LoginService service;

    @GetMapping("/login")
    public String loginForm(Model model){
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Login login, Model model, HttpSession session){
       try{
            service.login(login);
            session.setAttribute("login", "true");
            return "redirect:/home";
        }catch (LoginNotFound e){
            model.addAttribute("error", e.getMessage());
            return loginForm(model);
        }
    }
}
