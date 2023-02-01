package com.aina.headlines.controller;

import com.aina.headlines.service.HeadlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller()
public class FrontController {
    @Autowired
    HeadlineService headlineService;

    @GetMapping("/fo/search")
    public ModelAndView search (@RequestParam int page, @RequestParam String word){
        ModelAndView model = new ModelAndView();
        int size = 6;
        int offset = (page-1) * size;
        model.addObject("headlines", headlineService.search(word, offset, size));
        model.addObject("totalNumber", headlineService.count(word));
        model.addObject("page", page);
        model.addObject("pageName", "fo");
        System.out.println(headlineService.count(word));
        model.setViewName("fo-home");
        return model;
    }

    @GetMapping("/fo/home")
    public ModelAndView home (){
        ModelAndView model = new ModelAndView();
        return search(1, "");
    }
}
