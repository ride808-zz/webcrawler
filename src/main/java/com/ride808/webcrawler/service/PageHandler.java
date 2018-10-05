package com.ride808.webcrawler.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.*;
import java.util.*;

/*
 * The PageHandler is a static class responsible for taking a given page and processing all the links.  All links (internal)
 * to the domain/url passed in are added to the returned Set. Other external links or static content are written directly
 * to the SiteMap data structure for output of the application. This class can also be run as a standalone program by passing
 * in a URL to the main method.
 *
 *
 * @author Benjamin Somogyi <benjamin.somogyi@gmail.com>, 10/03/2018
 *
 */

@Service
public class PageHandler {


    /**
     *  Public method to exract links and static content from a given URL/Webpage.  Makes use of the jsoup library
     *  to put in place robust jquery like parsing of the page
     *
     * @param url
     * @return LinkedHashSet
     */

    public LinkedHashSet extractLinks(SiteMap mapInstance, URL url) {
        System.out.println("Starting to process page: " + url.toString());

        LinkedHashSet<URL> linkSet = new LinkedHashSet<URL>();

        // Extract the Domain so we can use it for storing external links
        URI uri = null;
        try {
            uri = new URI(url.toString());
        } catch (URISyntaxException e) {
            System.err.format("could not crawl %s : %s", url, e);
            return null;
        }
        String domain = uri.getHost();


        try {
            Document doc = Jsoup.connect(url.toString()).get();
            Elements links = doc.select("a[href]");
            Elements staticContent = doc.select("[src]");

            //Process all links on the page
            for (Element link : links) {
                String currentLink = link.attr("abs:href");

                if (currentLink.contains(domain)) {
                    linkSet.add(new URL(currentLink));
                } else { //It's an external Link, just add it to the SiteMap, we are not crawling it
                    mapInstance.putLink(currentLink, SiteMap.EXTERNAL);
                }
            }

            //Add all static content directly to the SiteMap
            for (Element src : staticContent) {
                if (src.tagName().equals("img"))
                    mapInstance.putLink(src.attr("abs:src"), SiteMap.STATIC);

                else { //Scripts, Other Media, etc
                    mapInstance.putLink(src.attr("abs:src"), SiteMap.STATIC);

                }
            }

            mapInstance.printJsonMap();

        } catch (IOException e) {
            System.err.format("Could not process webpage %s : %s", url, e);
            e.printStackTrace();
        }


        System.out.println("Finished to process page: " + url.toString());
        return linkSet;
    }

    public static void main(String[] args) {
        PageHandler handler = new PageHandler();
        try {
            if (args.length == 1) {

                //Capture the page type
                URL url = new URL(args[0]);
                System.out.println("Content-Type: " + url.openConnection().getContentType());

                SiteMap mapInstance = new SiteMap();

                //Linked Hasset to capture the unknown number of links that will be processed out of a given webpage
                LinkedHashSet links = handler.extractLinks(mapInstance,url);
                Iterator<URL> itr = links.iterator();
                while (itr.hasNext()) {
                    System.out.println(itr.next().toString());
                }

                mapInstance.printJsonMap();

            }
        } catch (Exception e) {
            System.err.println("An error occurred:");
            e.printStackTrace();
            ;
        }
    }

}
