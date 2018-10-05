package com.ride808.webcrawler.service;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SiteMapTests{

    @Test
    public void test_externalSiteAdded_IsCreatedSuccessfullyInSiteMap() {
        SiteMap mapInstance = new SiteMap();
        mapInstance.putLink("www.google.com", mapInstance.EXTERNAL);
        assertTrue("SiteMap should contain ExternalLinks", mapInstance.outputMap.containsKey(mapInstance.EXTERNAL));
    }

    @Test
    public void test_staticSiteAdded_IsCreatedSuccessfullyInSiteMap() {
        SiteMap mapInstance = new SiteMap();
        mapInstance.putLink("www.google.com", mapInstance.STATIC);
        assertTrue("SiteMap should contain StaticLinks", mapInstance.outputMap.containsKey(mapInstance.STATIC));
    }

    @Test
    public void test_twoDomainsAddedToMap_BothArePresentDomainList() {
        SiteMap mapInstance = new SiteMap();
        mapInstance.putLink("www.google.com", mapInstance.DOMAIN);
        mapInstance.putLink("www.google.com/test", mapInstance.DOMAIN);
        mapInstance.putLink("www.google.com/unique", mapInstance.DOMAIN);

        assertEquals("www.google.com", mapInstance.outputMap.get(mapInstance.DOMAIN).get(0));
        assertEquals("www.google.com/test", mapInstance.outputMap.get(mapInstance.DOMAIN).get(1));
        assertEquals("www.google.com/unique", mapInstance.outputMap.get(mapInstance.DOMAIN).get(2));
        assertTrue(mapInstance.exists("www.google.com/unique", mapInstance.DOMAIN));
    }

}
