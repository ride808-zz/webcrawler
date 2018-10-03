# webcrawler  [![master](https://travis-ci.org/ride808/webcrawler.svg?branch=master)](https://travis-ci.org/ride808/webcrawler/branches)

## Requirements
  
 - Develop a webcrawler that is limited to operating on a single domain  
 - Output of the webcrawler is a simple structured site map containing the following:  
   - Links to other pages under the same domain  
   - Links to external URLs  
   - Links to static content (i.e. images) for each respective page  

## Design Decisions & Trade-offs
  
- Speed & Memory Consumption of the Webcrawler  
  - Single Threaded Webcrawler  
  - Multi-Threaded Webcrawler  
  
- Webcrawler search algorithm  
  
  - DepthFS vs BreadthFS  
     
- Maintainability  
  - Extensible to add additional web crawlers and API driven  
     
- Usability:   
  - Simple RESTFul API  implementation to generate the sitemap.  Providing an API allows greater flexibility to add additional functionality to this utility  
     
- Management of State  
  - Determining what page has been visited by the crawler will require a data structure or backing database.  I chose to use java collections for this management, but if we were building a more robust search engine I'd go with a database that is fit for purpose.  
  

## Future Improvements

  
## Usage
    
 - Build  
 - Test
 - Run
