package com.crawler;


	import java.util.HashMap;
	import java.util.HashSet;
	import java.util.LinkedList;
	import java.util.Queue;
	import java.util.Set;

	public class WebCrawler
	{
	  // A static variable that will control the Maximum pages that can be traversed for a given URL
	  private static final int MAX_LIMIT_TO_SEARCH = 200;
	  
	  // All the pages we visit will be unique (the URL will be unique) so we can store the visited pages in a Data structure where duplicates are not allowed, in this case SET
	  private Set<String> pagesVisited;
	  
	  // A Queue to enable effective BFS traversal of the links, we can store the links to traversal in the queue and then in that order traverse all these links
	  private Queue<String> pagesToVisit;
	  
	  // HashMap to store page link and number of links on that page (This is optional as we are already storing this information into Database)
	  private HashMap<String, Integer> linksTable;

	  public WebCrawler(){
		  this.pagesVisited = new HashSet<String>(); 
		  this.pagesToVisit = new LinkedList<String>();
		  this.linksTable = new HashMap<>();
	  }
	  /**
	   * @param url
	   *            - The starting point of the crawler
	   */
	  public void countNumberOfLinks(String url)
	  {	

		  DBConnection dbConnect = new DBConnection(); 

		  this.pagesToVisit.add(url);
	      while(this.pagesVisited.size() < MAX_LIMIT_TO_SEARCH )
	      {
	    	  int numberOfLinks = Integer.MIN_VALUE;
	          String currentUrl;
	          Spider spidy = new Spider();
	          currentUrl = this.nextUrl();
	          if(currentUrl.equals("ListIsEmpty")) {
	        	  // No more links to traverse (In case if the input URL itself is not reachable the List will be empty)
            	  break; 
	          }

	          if(!currentUrl.startsWith("http")) {
	        	  // Invalid URL continue to next link
	        	  continue;
	          }
	          else {
	        	  // Call the crawler method that will parse the page and find all the links on that particular page
	              numberOfLinks= spidy.crawl(currentUrl);
	          }
	          if (numberOfLinks == -1) {
	        	  // Exception occurred while traversing the page for links
	        	  continue;
	          }
	          else {
	        	  // I am storing the page links and number of links on that page in a hashMap just if we need to use it further
	        	  linksTable.put(currentUrl, numberOfLinks);
	        	  
	        	  //Store the links and the associated count to a Table in AMAZON RDS cloud Database.
	        	  dbConnect.insertData(currentUrl, numberOfLinks);
	          }
	          this.pagesToVisit.addAll(spidy.getLinks());
	          //System.out.println("Total Visited : " + this.pagesVisited.size() + this.linksTable);
	      }

	      dbConnect.connectionClose();
	  }


	  /**
	   * 
	   * @return
	   * 	- next URL to visit (maintains the order in which they are found). I also make
	   * 	  sure that this method should not return a already traversed URL
	   */
	  private String nextUrl()
	  {
	      String nextUrl;
	      do
	      {	
	    	  if(!pagesToVisit.isEmpty())
	    		  nextUrl = this.pagesToVisit.poll();
	    	  else
	    		  return "ListIsEmpty";

	      } while(this.pagesVisited.contains(nextUrl));
	      this.pagesVisited.add(nextUrl);
	      return nextUrl;
	  }
}
