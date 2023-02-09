package com.aina.headlines.controller;

import com.aina.headlines.model.Headline;
import com.aina.headlines.service.HeadlineService;
import com.aina.headlines.service.HeadlineTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
        return "redirect:/bo/home";
    }

    @GetMapping("/bo/search")
    public ModelAndView search (@RequestParam int page, @RequestParam String word, @RequestParam Integer status){
        ModelAndView model = new ModelAndView();
        int offset = (page-1) * SIZE;
        model.addObject("headlines", headlineService.search(word, status, offset, SIZE));
        model.addObject("totalNumber", headlineService.count(word, status));
        model.addObject("status", status);
        model.addObject("page", page);
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
        return search(1, "", 0);
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

    @GetMapping("/headline/{id}/publier")
    public ModelAndView publishHeadline(@PathVariable Integer id, HttpSession session){
        ModelAndView model = new ModelAndView();
        if(session.getAttribute("login") == null ){
            model.setViewName("redirect:/login");
            return model;
        }
       headlineService.publish(id);
        model.setViewName("redirect:/bo/home");
        return model;
    }

    @PostMapping("/headline/{id}/datePublication")
    public ModelAndView setDatePublication(@PathVariable Integer id, HttpSession session, Headline headline){
        ModelAndView model = new ModelAndView();
        if(session.getAttribute("login") == null ){
            model.setViewName("redirect:/login");
            return model;
        }
        headlineService.setDatePublication(id, headline.getDatePubHtml());
        model.setViewName("redirect:/bo/home");
        return model;
    }
}
