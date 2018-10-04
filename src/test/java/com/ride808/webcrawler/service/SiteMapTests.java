package com.ride808.webcrawler.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SiteMapTests{

    @Test
    public void test_domainSiteAdded_IsCreatedSuccessfullyInSiteMap() {
        SiteMap map = new SiteMap();
        map.putLink("www.google.com", map.DOMAIN);
        assertTrue("SiteMap should contain DomainLinks", map.siteMap.containsKey(map.DOMAIN));
        assertTrue("Sitemap should not contain ExternalLinks", !map.siteMap.containsKey(map.EXTERNAL));
        assertTrue("Sitemap should not contain Static Content Links", !map.siteMap.containsKey(map.STATIC));
    }

    @Test
    public void test_externalSiteAdded_IsCreatedSuccessfullyInSiteMap() {
        SiteMap map = new SiteMap();
        map.putLink("www.google.com", map.EXTERNAL);
        assertTrue("SiteMap should contain ExternalLinks", map.siteMap.containsKey(map.EXTERNAL));
        assertTrue("Sitemap should not contain DomainLinks", !map.siteMap.containsKey(map.DOMAIN));
        assertTrue("Sitemap should not contain Static Content Links", !map.siteMap.containsKey(map.STATIC));
    }

    @Test
    public void test_staticSiteAdded_IsCreatedSuccessfullyInSiteMap() {
        SiteMap map = new SiteMap();
        map.putLink("www.google.com", map.STATIC);
        assertTrue("SiteMap should contain StaticLinks", map.siteMap.containsKey(map.STATIC));
        assertTrue("Sitemap should not contain ExternalLinks", !map.siteMap.containsKey(map.EXTERNAL));
        assertTrue("Sitemap should not contain DomainLinks", !map.siteMap.containsKey(map.DOMAIN));
    }

    @Test
    public void test_twoDomainsAddedToMap_BothArePresentDomainList() {
        SiteMap map = new SiteMap();
        map.putLink("www.google.com", map.DOMAIN);
        map.putLink("www.google.com/test", map.DOMAIN);

        assertEquals("www.google.com", map.siteMap.get(map.DOMAIN).get(0));
        assertEquals("www.google.com/test", map.siteMap.get(map.DOMAIN).get(1));
        assertTrue("Sitemap should not contain ExternalLinks", !map.siteMap.containsKey(map.EXTERNAL));
        assertTrue("Sitemap should not contain StaticLinks", !map.siteMap.containsKey(map.STATIC));
    }


}
