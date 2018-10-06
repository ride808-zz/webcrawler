package com.ride808.webcrawler.service;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class PageCrawlerTests {

    @Test
    public void test_malformedURL_IsNotValid() {
        PageCrawler crawler = new PageCrawler();

        String url = "malformedurl";

        assertTrue(!crawler.isValid(url));
    }

    @Test
    public void test_GoodURL_isValid() {
        PageCrawler crawler = new PageCrawler();

        String url = "http://wiprodigital.com";
        assertTrue(crawler.isValid(url));
    }

}
