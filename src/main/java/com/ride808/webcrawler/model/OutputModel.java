package com.ride808.webcrawler.model;

import java.util.List;
import java.util.Map;

/**
 *  Output model for Spring MVC resource for displaying a Map of Domain, External, and Static Content links discovered
 *  on a given website by the Crawler application
 *
 *  @author Benjamin Somogyi <benjamin.somogyi@gmail.com> 10/3/18
 */

public class OutputModel {
    Map<String, List<String>> outputMap;

    public OutputModel(Map<String, List<String>> outputMap){
        this.outputMap = outputMap;
    }

    public void setOutputMap(Map<String, List<String>> outputMap){
        this.outputMap = outputMap;
    }

    public Map<String, List<String>> getOutputMap(){
        return this.outputMap;
    }

}
