# webcrawler  [![master](https://travis-ci.org/ride808/webcrawler.svg?branch=master)](https://travis-ci.org/ride808/webcrawler/branches) 
## Requirements  
 - Develop a webcrawler that is limited to operating on a single domain    
 - Output of the webcrawler is a simple structured site map containing the following:    
	 - Links to other pages under the same domain    
	 - Links to external URLs    
	 - Links to static content (i.e. images) for each respective page    
  
## Design Decisions 

 - Speed & Memory Consumption of the Webcrawler    
     - Single Threaded Webcrawler  vs Multi-Threaded Webcrawler was implemented due to time constraints. Speed could be drastically increased with some more logic put around queue management and multi-threading the application.
    
 - Webcrawler search algorithm implements a Breadth First Search
       
       
 - Usability & Output:     
	 - No specifics provided in the requirments on output format. So, a Restful webservice was implementation to generate the sitemap and return it in json format.  Two APIs were provided.  One to allow a Post/Submit of a URL to be mapped  where the client will receive an asyncronous response back. And a second API to allow all SiteMaps to be retrieved with a GET Operation. 
       
 - Management of State    
	 - Determining what page has been visited by the crawler will require data structures.  I chose to use java collections for this management. 
  	      - Visited Sites: A Java Set as a collection of unique urls will be used for managing the collection of urls that have been visited by the application.
  	      - Processing Queue: A single thread operates on a Queue (LinkedList), containing all the links for a particular page. Once all objects on the queue have been processed the thread will terminate and a SiteMap is completed.
  	  
  	 - An embedded database  (JPA) was utilized for this use case to store the SiteMap objects until they are retrieved through the RestFul API.
    
  
## Future Improvements  
  
 -  Multi-Threaded application: The speed of the application could be greatly increased by adding additional threads to crawl the webpages. This would require iterations on queue management and syncronization of crawlers operating on the SiteMap.
 -  More Robust Testing.  Addition of a mocking framework as well.
 -  Add support for the Robots Exclusion Protocol
      
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
	```
	 
	 - Using Docker:

	```
	docker pull bsomogyi/webcrawler:latest   
	docker run -d -p 8080:8080 bsomogyi/webcrawler:latest  
	```
	**Restful Endpoints Once App Is Running:**
	
	**POST**
	```
	curl -i -X POST -H "Content-Type:application/json" -d "http://wiprodigital.com" http://localhost:8080/createMap
	```
	
	 Ex. Response:

	```
   HTTP/1.1 202 
   Content-Type: application/json;charset=UTF-8
   Content-Length: 56
   Date: Fri, 05 Oct 2018 22:58:22 GMT

   URL http://wiprodigital.com Accepted for Processing	
	```
	
	**GET**
    ```
	curl -i -X GET http://localhost:8080/getMap
   ```
	Ex. Response:

	```
	[
	  {
	    "domain" : "http://olddoesntexisturl.com",
	    "outputMap" : {
	      "DomainLinks" : [
	        "http://olddoesntexisturl.com"
	      ],
	      "StaticContentLinks" : [ ],
	      "ExternalLinks" : [ ]
	    }
	  }
	]		
	```
	
## Design

WebCrawler State Diagram: 

![statediagram](https://github.com/ride808/webcrawler/blob/master/docs/images/state-diagram.png)

