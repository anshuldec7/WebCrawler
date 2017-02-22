package com.crawler;

public class CrawlerTest
{
    /**
     * This is my test. It creates a web crawler which creates a spider that crawls the web.
     * 
     * @param args
     *            - not used
     */
    public static void main(String[] args)
    {
        WebCrawler myCrawler = new WebCrawler();
        //input URL for the crawler
        myCrawler.countNumberOfLinks("http://www.yahoo.com/");
    }
    
    /**
     * For accepting the URL as an argument following code can be used. 
     * 
     * @param args
     *            - URL to traverse
     */
    /*    
    public static void main(String[] args)
    {
    	String url = args[0];
        WebCrawler myCrawler = new WebCrawler();
        myCrawler.countNumberOfLinks(url);
    }
    */
    
}
