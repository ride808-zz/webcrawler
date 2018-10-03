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
	 - Keep code extensible to allow for additional web crawlers or expanded functionality   
       
 - Usability:     
	 - Simple RESTFul API  implementation to generate the sitemap.  Providing an API allows greater flexibility to add additional functionality to this utility    
       
 - Management of State    
	 - Determining what page has been visited by the crawler will require a data structure or backing database.  I chose to use java collections for this management, but if we were building a more robust search engine I'd go with a database that is fit for purpose.    
    
  
## Future Improvements  
  
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
