package com.ride808.webcrawler.service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
*  The MapBuilder Class is for structing the output of all sites gathered from th the webcrawler
*  application.  It includes the functionality to syncronize threads as they operate on the data structure, adding new
*  internal, external, and static links.
*
*  @author Benjamin Somogyi <benjamin.somogyi@gmail.com> 10/3/18
*/

@Service
public class MapBuilder {
    public static final String DOMAIN = "DomainLinks";
    public static final String EXTERNAL = "ExternalLinks";
    public static final String STATIC = "StaticContentLinks";
    HashMap<String, Set<String>> outputMap;


    public MapBuilder() {
        outputMap = new HashMap<String, Set<String>>();
        outputMap.put(DOMAIN, new HashSet<String>());
        outputMap.put(EXTERNAL, new HashSet<String>());
        outputMap.put(STATIC, new HashSet<String>());
    }

    /**
     * Given a url string and a link type (DOMAIN, EXTERNAL, STATIC), a boolean value is returned if the url exists in
     * the outputMap
     *
     * @param url
     * @param type
     * @return boolean
     */

    public boolean exists(String url, String type){

       return outputMap.get(type).contains(url);
    }

    public HashMap<String, Set<String>> getOutputMap(){
        return this.outputMap;
    }
    /**
     *  Adds the url string to the outputMap under the given key/type
     *
     * @param url
     * @param type
     */

    public void putLink(String url, String type) {
        synchronized (outputMap) {

            Set<String> valueList = outputMap.get(type);
            if (valueList == null) {
                valueList = new HashSet<>();
                switch (type) {
                    case DOMAIN:
                        outputMap.put(type, valueList);
                        break;

                    case EXTERNAL:
                        outputMap.put(type, valueList);
                        break;

                    case STATIC:
                        outputMap.put(type, valueList);
                        break;
                }
            }
            valueList.add(url);
        }
    }

    /**
     *  Return the outputMap as a Json formated String
     * @return
     */

    public String getJsonSiteMap() {

        ObjectMapper mapper = new ObjectMapper();
        String json = "";

        try {

            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            DefaultPrettyPrinter prettyPrinter = new DefaultPrettyPrinter();
            prettyPrinter.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
            json = mapper.writer(prettyPrinter).writeValueAsString(this.outputMap);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    /**
     * Helper method for printing the json outputMap
     */
    public void printJsonMap() {
        System.out.println(getJsonSiteMap());
    }

}
