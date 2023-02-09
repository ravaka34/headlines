package com.aina.headlines.controller;

import com.aina.headlines.exception.LoginNotFound;
import com.aina.headlines.model.Login;
import com.aina.headlines.model.LoginAuteur;
import com.aina.headlines.service.LoginAuteurService;
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

    @Autowired
    LoginAuteurService loginAuteurService;

    @GetMapping("/login")
    public String loginForm(Model model){
        return "login";
    }

    @GetMapping("/loginAuteur")
    public String loginAuteurForm(){
        return "loginAuteur";
    }

    @GetMapping("/logoutAdmin")
    public String logOut(HttpSession session){
        session.removeAttribute("login");
        return "redirect:/bo/home";
    }

    @GetMapping("/logoutAuteur")
    public String logOutAuteur(HttpSession session){
        session.removeAttribute("loginAuteur");
        return "redirect:/fo/home";
    }



    @PostMapping("/login")
    public String login(@ModelAttribute Login login, Model model, HttpSession session){
       try{
            service.login(login);
            session.setAttribute("login", "true");
            return "redirect:/bo/home";
        }catch (LoginNotFound e){
            model.addAttribute("error", e.getMessage());
            return loginForm(model);
        }
    }

    @PostMapping("/loginAuteur")
    public String loginAuteur(@ModelAttribute LoginAuteur login, Model model, HttpSession session){
        try{
            loginAuteurService.login(login);
            session.setAttribute("loginAuteur", "true");
            return "redirect:/fo/home";
        }catch (LoginNotFound e){
            model.addAttribute("error", e.getMessage());
            return loginAuteurForm();
        }
    }
}
