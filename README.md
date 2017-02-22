# WebCrawler
__Java Application for Crawling the Web with a given URL, up to a given maximum number of pages implementing BFS__
## What does it do ?
- [x] Takes a URL as an input.
- [x] Crawl through the given URL page and finds all the links on that page.
- [x] Counts the number of links and store the information as a Key Value pair in a HashMap as well as in a Database Table.
- [x] Crawl through first 200 pages of the given URL in a Breadth First Search manner.
- [x] Error handling in case there are fewer url (less than 200) or if the a URL sends bad response.

###Basic Idea (Algorithm)
1. Create a WebCrawler class.
   * Define a Static variable to ensure we only traverse through first 200 pages.
   
     ```java
     private static final int MAX_LIMIT_TO_SEARCH = 200;
     ```
   * Define a Set to take account of visited pages (This is to ensure we traverse a page only once)
   
     ```java
     private Set<String> pagesVisited;
     ```
   * Define a Queue to take account of all the pages that we need to traverse (Initialise it with the given URL)
   
     ```java	  
     private Queue<String> pagesToVisit;
     ```
   * Define a Method "countNumberOfLinks" that creates an object of Spider Class (Will be creating soon) and pass the URL and      loop this till we reach either 200 pages or there are no more pages left for traversal
2. Create a Spider class.
   * Define a USER_AGENT to make sure that Web server does not treat our crawler as a robot instead treat it as a browser.
