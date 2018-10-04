package com.ride808.webcrawler.service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;
import java.util.*;

public class SiteMap {
    public static final String DOMAIN = "DomainLinks";
    public static final String EXTERNAL = "ExternalLinks";
    public static final String STATIC = "StaticContentLinks";
    Map<String, List<String>> siteMap;
    List<String> domainLinks;
    List<String> externalLinks;
    List<String> staticContentLinks;

    public SiteMap() {
        domainLinks = new ArrayList<String>();
        externalLinks = new ArrayList<String>();
        staticContentLinks = new ArrayList<String>();
        siteMap = Collections.synchronizedMap(new HashMap<String, List<String>>());
    }

    public void putLink(String url, String type) {
        synchronized (siteMap) {

            //SiteMap already contains a list of the specific Link Type, just add new url
            if (siteMap.containsKey(type)) {
                siteMap.get(type).add(url);
            } else {  //New link type add the new List containing the url to the Map
                switch (type) {
                    case DOMAIN:
                        domainLinks.add(url);
                        siteMap.put(type, domainLinks);
                        break;

                    case EXTERNAL:
                        externalLinks.add(url);
                        siteMap.put(type, externalLinks);
                        break;

                    case STATIC:
                        staticContentLinks.add(url);
                        siteMap.put(type, staticContentLinks);
                        break;
                }
            }
        }
    }

    public String getJsonSiteMap() {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = "";

        try {

            ObjectMapper mapper = new ObjectMapper();
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            json = writer.writeValueAsString(this.siteMap);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    public void printJsonMap() {
        System.out.println(getJsonSiteMap());
    }

    public void printMap() {
        for (Map.Entry<String, List<String>> e : this.siteMap.entrySet()) {
            System.out.println(e.getKey() + ":");
            for (String e1 : e.getValue()) {
                System.out.println(e.getKey() + " = " + e1);
            }
            System.out.println();
        }
    }
}
