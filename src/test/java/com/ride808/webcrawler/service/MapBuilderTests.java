package com.ride808.webcrawler.service;

import com.ride808.webcrawler.service.MapBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MapBuilderTests {

    @Test
    public void test_externalSiteAdded_IsCreatedSuccessfullyInSiteMap() {
        MapBuilder mapInstance = new MapBuilder();
        mapInstance.putLink("www.google.com", mapInstance.EXTERNAL);
        assertTrue("SiteMap should contain ExternalLinks", mapInstance.outputMap.containsKey(mapInstance.EXTERNAL));
    }

    @Test
    public void test_staticSiteAdded_IsCreatedSuccessfullyInSiteMap() {
        MapBuilder mapInstance = new MapBuilder();
        mapInstance.putLink("www.google.com", mapInstance.STATIC);
        assertTrue("SiteMap should contain StaticLinks", mapInstance.outputMap.containsKey(mapInstance.STATIC));
    }

    @Test
    public void test_twoDomainsAddedToMap_BothArePresentDomainList() {
        MapBuilder mapInstance = new MapBuilder();
        mapInstance.putLink("www.google.com", mapInstance.DOMAIN);
        mapInstance.putLink("www.google.com/test", mapInstance.DOMAIN);
        mapInstance.putLink("www.google.com/unique", mapInstance.DOMAIN);

        assertTrue("www.google.com", mapInstance.outputMap.get(mapInstance.DOMAIN).contains("www.google.com"));
        assertTrue("www.google.com/test", mapInstance.outputMap.get(mapInstance.DOMAIN).contains("www.google.com/test"));
        assertTrue("www.google.com/unique", mapInstance.outputMap.get(mapInstance.DOMAIN).contains("www.google.com/unique"));
    }

}
