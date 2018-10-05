package com.ride808.webcrawler.entity;

import javax.persistence.*;
import java.util.Set;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Output entity for Spring MVC resource for displaying a Map of Domain, External, and Static Content links discovered
 *  on a given website by the Crawler application
 *
 *  @author Benjamin Somogyi <benjamin.somogyi@gmail.com> 10/3/18
 */

@Entity
public class SiteMap {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    String domain;

    @Column(length = 16777210)
    HashMap<String, Set<String>> outputMap;

    public void setOutputMap(HashMap<String, Set<String>> outputMap){
        this.outputMap = outputMap;
    }

    public HashMap<String, Set<String>> getOutputMap(){

        return this.outputMap;
    }

    public String getDomain(){
        return this.domain;
    }

    public void setDomain(String domain){
        this.domain= domain;
    }

}
