package com.aina.headlines.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public ModelAndView hello(){
        ModelAndView model = new ModelAndView();
        model.setViewName("hello");
        return model;
    }
}
