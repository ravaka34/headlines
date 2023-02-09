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


@Controller()
public class FrontController {
    @Autowired
    HeadlineService headlineService;
    @Autowired
    HeadlineTypeService headlineTypeService;

    private static final String UPLOAD_DIRECTORY ="/images";

    @GetMapping("/fo/search")
    public ModelAndView search (@RequestParam int page, @RequestParam String word){
        ModelAndView model = new ModelAndView();
        int size = 6;
        int offset = (page-1) * size;
        model.addObject("headlines", headlineService.searchFo(word, offset, size));
        model.addObject("totalNumber", headlineService.countFo(word));
        model.addObject("page", page);
        model.addObject("pageName", "fo");
        model.setViewName("fo-home");
        return model;
    }

    @GetMapping("/fo/home")
    public ModelAndView home (){
        ModelAndView model = new ModelAndView();
        return search(1, "");
    }

    @GetMapping("/fo/headline-form")
    public ModelAndView createForm(HttpSession session){
        ModelAndView model = new ModelAndView();
        if(session.getAttribute("loginAuteur") == null ){
            model.setViewName("redirect:/loginAuteur");
            return model;
        }
        model.addObject("types", headlineTypeService.getAll());
        model.setViewName("fo-headline-form");
        return model;
    }

    @PostMapping("/fo/headline")
    public String create(@ModelAttribute Headline headline, HttpSession session) throws IOException {
        if(session.getAttribute("loginAuteur") == null ){
            return "redirect:/loginAuteur";
        }
        headlineService.create(headline, session.getServletContext().getRealPath(UPLOAD_DIRECTORY));
        return "redirect:/fo/home";
    }
}
