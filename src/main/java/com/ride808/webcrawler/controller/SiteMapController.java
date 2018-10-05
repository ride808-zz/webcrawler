package com.ride808.webcrawler.controller;

import com.ride808.webcrawler.model.OutputModel;
import com.ride808.webcrawler.service.PageHandler;
import com.ride808.webcrawler.service.SiteMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;

/**
 *
 * Spring Boot Controller for Processing/Crawling a give URL
 *
 *  @author Benjamin Somogyi <benjamin.somogyi@gmail.com> 10/3/18
 */

@RestController
public class SiteMapController {
    private static Logger log = LoggerFactory.getLogger(SiteMapController.class);


    @Autowired
    private PageHandler pageHandler;

    /**
     * GET Resource to retrive the internal, external, and static content links from a specified url
     * @param url
     * @return
     */
    @RequestMapping(value = "/sitemap", method = RequestMethod.GET)
    public OutputModel getMap(@RequestParam(value = "url") String url){
        SiteMap mapInstance = new SiteMap();

        try {
            pageHandler.extractLinks(mapInstance, new URL(url));
        }catch(MalformedURLException e){
           log.error("Mallformed URL Exception:" + url);
        }
        OutputModel model = new OutputModel(mapInstance.getOutputMap());

        return model;
    }
}
