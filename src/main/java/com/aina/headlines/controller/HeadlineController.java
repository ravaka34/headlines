package com.aina.headlines.controller;

import com.aina.headlines.HibernateDao;
import com.aina.headlines.model.HeadlineType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HeadlineController {

    @Autowired
    HibernateDao dao;

    @GetMapping("/home")
    public ModelAndView home (){
        ModelAndView model = new ModelAndView();
        model.setViewName("example");
        return model;
    }

    @GetMapping("/headline-form")
    public ModelAndView createForm(){
        ModelAndView model = new ModelAndView();
        model.addObject("types", dao.findAll(HeadlineType.class));
        model.setViewName("headline-form");
        return model;

    }
}
