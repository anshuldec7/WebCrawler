package com.crawler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.jsoup.HttpStatusException;
import java.net.SocketTimeoutException;

public class Spider
{
    // USER_AGENT is used here to make web server thinks the crawler is a normal web browser.
    private static final String USER_AGENT =
           "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    // A Queue to store all the links in the page we are traversing currently
    private Queue<String> links;
    
    
    public Spider() {
    	this.links = new LinkedList<String>();
    }


    /**
     * This performs most of the work. It makes an HTTP request, checks the response, and then gathers
     * up all the links on the page.
     * 
     * @param url
     *            - The URL to visit
     * @return Total no. of links on the input URL and error code in case of exception
     */
    public int crawl(String url) {
        try {
        	//I used JSoup library to parse the content of an HTML page
        	Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
        	Document htmlDocument = connection.ignoreContentType(true).get();
            Elements linksOnPage = htmlDocument.select("a[href]");
            
            for(Element link : linksOnPage) {
                this.links.add(link.absUrl("href"));
            } 
            return linksOnPage.size();
        } catch(HttpStatusException hte) {
        	System.out.print("Status Exception :" + hte);
            return -1;
        } catch (SocketTimeoutException ste) {
        	System.out.print("SocketTimeoutException :" + ste);
        	return -1;
		} catch (IOException ioe) {
        	System.out.print("Exception: " + ioe);
        	return -1;
		}
        catch (Exception e) {
        	System.out.print("Exception: " + e);
        	return -1;
		}
        
    }
    // Getter for Queue
    public Queue<String> getLinks()
    {
        return this.links;
    }

}
