package com.ride808.webcrawler.service.impl;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ride808.webcrawler.entity.SiteMap;
import com.ride808.webcrawler.repository.SiteMapRepository;
import com.ride808.webcrawler.service.LinkQueue;
import com.ride808.webcrawler.service.MapBuilder;
import com.ride808.webcrawler.service.PageCrawler;
import com.ride808.webcrawler.service.SiteMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;

/**
 *  SiteMapServiceImpl is the implementation Class for the SiteMap Service interface. It is responsible for saving
 *  a new SiteMap object after Crawling is complete on a particular URL as well as retrieving the Object back for
 *  usage.
 *
 * @Author Ben Somogyi <benjamin.somogyi@gmail.com> 10/3/18
 */

@Service
public class SiteMapServiceImpl implements SiteMapService{

    @Autowired
    SiteMapRepository siteMapRepository;

    /**
     * Save method for calling the webcrawler and producing the Site Map to be returned to the user
     * @param url
     */
    public void save(String url){

        SiteMap siteMap = new SiteMap();
        siteMap.setDomain(url);

        PageCrawler pageCrawler = new PageCrawler();
        LinkQueue linkQueue = new LinkQueue();
        MapBuilder mapBuilder = new MapBuilder();

        linkQueue.push(url);

        pageCrawler.extractLinks(mapBuilder, linkQueue, url);

        siteMap.setOutputMap(mapBuilder.getOutputMap());

        siteMapRepository.save(siteMap);
    }

    /**
     * Returns a json representation of the SiteMap to the end user
     * @return
     */

    public String getSiteMap(){
        ObjectMapper mapper = new ObjectMapper();
        String json = "";

        try {

            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
            prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
            json = mapper.writer(prettyPrinter).writeValueAsString(siteMapRepository.findAll());

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }
}
