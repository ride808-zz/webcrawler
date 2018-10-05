package com.ride808.webcrawler.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.Map;

/**
 *  Output entity for Spring MVC resource for displaying a Map of Domain, External, and Static Content links discovered
 *  on a given website by the Crawler application
 *
 *  @author Benjamin Somogyi <benjamin.somogyi@gmail.com> 10/3/18
 */

@Entity
public class OutputModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    Map<String, List<String>> outputMap;
    String domain;

    public void setOutputMap(Map<String, List<String>> outputMap){
        this.outputMap = outputMap;
    }

    public Map<String, List<String>> getOutputMap(){

        return this.outputMap;
    }

    public String getUrl(){
        return this.domain;
    }

    public void setUrl(String domain){
        this.domain= domain;
    }

}
