package com.aina.headlines.controller;

import com.aina.headlines.model.Headline;
import com.aina.headlines.service.HeadlineService;
import com.aina.headlines.service.HeadlineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class BackOfficeController {
    @Autowired
    HeadlineService headlineService;

    @Autowired
    HeadlineTypeService headlineTypeService;

    private static final String UPLOAD_DIRECTORY ="/images";
    private static final int SIZE = 6;


    @PostMapping("/headline")
    public String create(@ModelAttribute Headline headline, HttpSession session) throws IOException {
        if(session.getAttribute("login") == null ){
            return "redirect:/login";
        }
        headlineService.create(headline, session.getServletContext().getRealPath(UPLOAD_DIRECTORY));
        return "redirect:/home";
    }

    @GetMapping("/bo/search")
    public ModelAndView search (@RequestParam int page, @RequestParam String word){
        ModelAndView model = new ModelAndView();
        int offset = (page-1) * SIZE;
        model.addObject("headlines", headlineService.search(word, offset, SIZE));
        model.addObject("totalNumber", headlineService.count(word));
        model.addObject("page", page);
        System.out.println(headlineService.count(word));
        model.setViewName("bo-home");
        return model;
    }

    @GetMapping("/bo/home")
    public ModelAndView home (HttpSession session){
        ModelAndView model = new ModelAndView();
        if(session.getAttribute("login") == null ){
            model.setViewName("redirect:/login");
            return model;
        }
        return search(1, "");
    }

    @GetMapping("/bo/headline-form")
    public ModelAndView createForm(HttpSession session){
        ModelAndView model = new ModelAndView();
        if(session.getAttribute("login") == null ){
            model.setViewName("redirect:/login");
            return model;
        }
        model.addObject("types", headlineTypeService.getAll());
        model.setViewName("headline-form");
        return model;
    }
}
