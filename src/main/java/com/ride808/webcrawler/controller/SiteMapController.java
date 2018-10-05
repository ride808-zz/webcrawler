package com.ride808.webcrawler.controller;

import com.ride808.webcrawler.service.SiteMapService;
import org.slf4j.Logger;
import java.util.concurrent.ForkJoinPool;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.*;
import java.io.IOException;

/**
 *
 * Spring Boot Controller for Processing/Crawling a give URL.  Implemented as a RestFul API with Asyncronous Responses
 * for creations of new site Maps.
 *
 *  @author Benjamin Somogyi <benjamin.somogyi@gmail.com> 10/3/18
 */

@RestController
public class SiteMapController {
    private static Logger log = LoggerFactory.getLogger(SiteMapController.class);

    @Autowired
    private SiteMapService siteMapService;

    /**
     *  POST Endpoint used for receiving a url and responding asyncronously to the client
     * @param url
     * @return response
     */

    @RequestMapping(value = "/createMap", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createSiteMap(@RequestBody String url) {
        try {
            URL newUrl = new URL(url);
            URLConnection conn = newUrl.openConnection();
            conn.connect();
        } catch (MalformedURLException e) {
            String response = "Malformed URL. Unable to Process: " + url;
            return new ResponseEntity<String>(response,HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            String response = "Cannot Connect to URL. Unable to Process: " + url;
            return new ResponseEntity<String>(response,HttpStatus.BAD_REQUEST);
        }

        String response = "URL " + url + " Accepted for Processing";

        ForkJoinPool.commonPool().submit(() -> {
            try {
                siteMapService.save(url);
            } catch (Exception e) {

            }
        });
        return new ResponseEntity<String>(response,HttpStatus.ACCEPTED);
    }

    /**
     * Returns Json response of all SIteMaps procesed by the application
     * @return  response
     *
    */

    @RequestMapping(value = "/getMap", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getAllSiteMaps(){
        String siteMaps = siteMapService.getSiteMap();

        return siteMaps;
    }
}
