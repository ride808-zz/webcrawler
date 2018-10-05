# webcrawler  [![master](https://travis-ci.org/ride808/webcrawler.svg?branch=master)](https://travis-ci.org/ride808/webcrawler/branches) 

## Requirements  
 - Develop a webcrawler that is limited to operating on a single domain    
 - Output of the webcrawler is a simple structured site map containing the following:    
	 - Links to other pages under the same domain    
	 - Links to external URLs    
	 - Links to static content (i.e. images) for each respective page    
  
## Design Decisions 

 - Speed & Memory Consumption of the Webcrawler    
     - Single Threaded Webcrawler  vs Multi-Threaded Webcrawler was implemented due to time constraints 
    
 - Webcrawler search algorithm implements a Breadth First Search
       
       
 - Usability & Output:     
	 - No specifics provided in the requirments on output format. So, a webservice was implementation to generate the sitemap return it in json format.  Providing an API allows greater flexibility to add additional functionality to this utility    
       
 - Management of State    
	 - Determining what page has been visited by the crawler will require a data structure or backing database.  I chose to use java collections for this management, but if we were building a more robust search engine I'd go with a database that is fit for purpose.    
  	      - Visited Sites: A Java Set as a collection of unique urls will be used for managing the collection of urls that have been visited by the application.
  	      - Processing Queue: A thread will operate on a Queue containing all the links for a particular page. Once all objects on the queue have been processed the thread will terminate
    
  
## Future Improvements  
  
 -  Multi-Threaded application: The speed of the application could be greatly increased by adding addional threads to 
 crawl the webpages.
 -  Implement RestFul APIs so the request to crawl a page is asyncronous with additional endpoints to go back and retieve
 the siteMap.
 - Asyncronous response from API
 - More Robust Testing
      
## Usage  
 - Build  & Test  
	 - Prequisites:
		 - Java 1.8  
		 - Maven 3.x  

	```		 
	git clone https://github.com/ride808/webcrawler.git  
	cd webcrawler 
	mvn clean install
	```
 - Run  
  
	 - Using Java 1.8:

	``` 
	java -jar target/webcrawler-0.0.1-SNAPSHOT.jar  
	curl http://localhost:8080/sitemap?url=www.google.com     	 
	```
	 
	 - Using Docker:

	```
	docker pull bsomogyi/webcrawler:latest   
	docker run -d -p 8080:8080 bsomogyi/webcrawler:latest  
	curl http://localhost:8080/sitemap?url=www.google.com  
	```

## Design

WebCrawler State Diagram: 

![statediagram](https://github.com/ride808/webcrawler/blob/master/docs/images/state-diagram.png)

