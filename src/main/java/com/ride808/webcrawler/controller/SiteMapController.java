package com.ride808.webcrawler.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiteMapController {
    @RequestMapping("/sitemap")
    public String getMap(@RequestParam(value = "url") String name){
        return "Requested Site to Be Mapped:" + name;
    }
}
