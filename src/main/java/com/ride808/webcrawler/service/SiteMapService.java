package com.ride808.webcrawler.service;

/**
 * Inteface Class for SiteMapService
 *
 * @Author Ben Somogyi  <benjamin.somogyi@gmail.com> 10/3/18
 */

public interface SiteMapService {

    public String getSiteMap();
    public void save(String url);

}
