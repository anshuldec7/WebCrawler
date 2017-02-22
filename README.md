# Web Crawler
__Java Application for Crawling the Web with a given URL up to a given maximum number of pages implementing BFS__

*Note: I have removed my RDS database credential although you can create your own and replace your username and passowrd at line 15 and 16 in file [DBConnection.java](https://github.com/anshuldec7/WebCrawler/blob/master/Crawler/src/com/crawler/DBConnection.java):*

  ```java
	static final String USER = "YOUR_USERNAME";
	static final String PASS = "YOUR_PASSWORD";
  ```


## What does it do ?
- [x] Takes a URL as an input.
- [x] Crawl through the given URL page and finds all the links on that page.
- [x] Counts the number of links and store the information as a Key Value pair in a HashMap as well as in a Database Table.
- [x] Crawl through first 200 pages of the given URL in a Breadth First Search manner.
- [x] Error handling in case there are fewer url (less than 200) or if the URL sends bad response.

## Technologies
> Java 8

> Jsoup for URL parsing

> Amazon RDS (MySQL)

###Basic Idea 
1. Create a WebCrawler class.
   * Define a Static variable to ensure we only traverse through first 200 pages.
   
     ```java
     private static final int MAX_LIMIT_TO_SEARCH = 200;
     ```
   * Define a Set to take account of visited pages (This is to ensure we traverse a page only once).
   
     ```java
     private Set<String> pagesVisited;
     ```
   * Define a Queue to take account of all the pages that we need to traverse (Initialise it with the given URL).
   
     ```java	  
     private Queue<String> pagesToVisit;
     ```
   * Define a Method "countNumberOfLinks" that creates an object of Spider Class (Will be creating soon) and pass the URL and      loop this till we reach either 200 pages or there are no more pages left for traversal.
2. Create a Spider class.
   * Define a USER_AGENT to make sure that Web server does not treat our crawler as a robot instead treat it as a browser.
   * Define a Queue that will collect all the links for a particular URL traversal and adds it to the main Queue in WebCrawler      class.
   * Used JSOUP library to parse and return a HTML document object.
   
3. Create a database Connection class.
   * Connect to Amazon RDS MySql database and insert the data into a table LinksDetails.
   * Sample Data for input URL : https://www.yahoo.com/
   
        Link | Count
        ---- | -----
        http://www.yahoo.com/ |  101
        https://mail.yahoo.com/ | 230
        .... | ....
        .... | ....

:beers: *Cheers !!* 



   
   
   
