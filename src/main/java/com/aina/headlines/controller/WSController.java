package com.aina.headlines.controller;

import com.aina.headlines.model.Headline;
import com.aina.headlines.service.HeadlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WSController {

    @Autowired
    HeadlineService headlineService;

    @GetMapping("/ws/headlines")
    public List<Headline> lines (){
        return headlineService.getAll();
    }
}
