package com.ride808.webcrawler.service;

import com.ride808.webcrawler.controller.SiteMapController;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.*;
import java.util.*;

/**
 * The PageCrawler is a static class responsible for taking a given page and processing all the links.  All links (internal)
 * to the domain/url passed in are added to the returned Set. Other external links or static content are written directly
 * to the SiteMap data structure for output of the application. This class can also be run as a standalone program by passing
 * in a URL to the main method.
 *
 * @author Benjamin Somogyi <benjamin.somogyi@gmail.com>, 10/03/2018
 */

@Service
public class PageCrawler {

    private static Logger log = LoggerFactory.getLogger(PageCrawler.class);

    /**
     * Public method to exract links and static content from a given URL/Webpage.  Makes use of the jsoup library
     * to put in place robust jquery like parsing of the page
     *
     * @param url
     * @return LinkedHashSet
     */

    public void extractLinks(SiteMap mapInstance, LinkQueue linkQueue, String url) {

        String domain = "";
        if (isValid(url)) {
            domain = getUri(url).getHost();
            log.info("Processing Domain= " + domain);
        }
        else{
            log.error("Unable to process URL " + url + " Exiting...");
            return;
        }


        while (!linkQueue.isEmpty()) {
            url = linkQueue.pop();

            //Store the page to be processed as visited
            if (!mapInstance.exists(url, SiteMap.DOMAIN)) {
                mapInstance.putLink(url, SiteMap.DOMAIN);
            } else {  //If the pate was visited, skip it.
                continue;
            }

            log.info("Processing page " + url);


            try {
                Document doc = Jsoup.connect(url).get();
                Elements links = doc.select("a[href]");
                Elements staticContent = doc.select("[src]");

                //Process all links on the page
                for (Element link : links) {

                    String currentLink = link.attr("abs:href");

                    if (isValid(currentLink)) {


                        try {
                            if (getUri(currentLink).getHost().contains(domain)) { //It's a domain link

                                //Only store the domain link in the queue if it wasn't already visited
                                if (!mapInstance.exists(currentLink, SiteMap.DOMAIN)) {
                                    linkQueue.push(currentLink);
                                }
                            } else { //Process external Links directly to SiteMap
                                if (!mapInstance.exists(currentLink, SiteMap.EXTERNAL)) {
                                    mapInstance.putLink(currentLink, SiteMap.EXTERNAL);
                                }
                            }
                        } catch (Exception e) {
                            log.error("Unable to process URL " + currentLink + " due to exception");
                        }
                    }
                }

                //Process static content directly to the SiteMap
                for (Element src : staticContent) {
                    String srcLink = src.attr("abs:src");

                    if (src.tagName().equals("img")) {
                        if (!mapInstance.exists(srcLink, SiteMap.STATIC)) {
                            mapInstance.putLink(srcLink, SiteMap.STATIC);
                        }

                    } else { //Scripts, Other Media, etc
                        if (!mapInstance.exists(srcLink, SiteMap.STATIC)) {
                            mapInstance.putLink(srcLink, SiteMap.STATIC);
                        }
                    }
                }

                //mapInstance.printJsonMap();

            } catch (IOException e) {
                log.error("Could not proces webpage %s : %s", url, e);
            }
            //System.out.println("Finished to process page: " + url.toString());
        }

    }

    /* Returns true if url is valid */
    public static boolean isValid(String url) {
        /* Try creating a valid URL */
        try {
            new URL(url).toURI();
            return true;
        }

        // If there was an Exception
        // while creating URL object
        catch (Exception e) {
            return false;
        }
    }

    private URI getUri(String url) {
        // Extract the Domain so we can use it for storing external links
        URI uri = null;
        try {
            uri = new URI(url);
        } catch (URISyntaxException e) {
        }
        return uri;
    }

    public static void main(String[] args) {
        PageCrawler handler = new PageCrawler();
        try {
            if (args.length == 1) {

                //Capture the page type
                URL url = new URL(args[0]);

                //System.out.println("Content-Type: " + url.openConnection().getContentType());

                SiteMap mapInstance = new SiteMap();
                LinkQueue linkQueue = new LinkQueue();
                linkQueue.push(url.toString());

                //Linked Hasset to capture the unknown number of links that will be processed out of a given webpage
                handler.extractLinks(mapInstance, linkQueue, url.toString());

                mapInstance.printJsonMap();

            }
        } catch (Exception e) {
            System.err.println("An error occurred:");
            e.printStackTrace();
            ;
        }
    }

}
