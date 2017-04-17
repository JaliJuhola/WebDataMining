
package src;

import files.Log;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import sortedlinks.Link;
public class SiteVisitor {
        private static final String USER_AGENT =
            "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/13.0.782.112 Safari/535.1";
    private List<String> links = new LinkedList<String>();
    private Document htmlDocument;
    private String url;
    
        public boolean visit(Link link)
    {
        try
        {
            this.url = link.getWithHTTP();
            Connection connection = Jsoup.connect(url).userAgent(USER_AGENT);
            Document htmlDocument = connection.get();
            this.htmlDocument = htmlDocument;
            if(connection.response().statusCode() == 200) // 200 is the HTTP OK status code
                                                          // indicating that everything is great.
            {
                
            }
            if(!connection.response().contentType().contains("text/html"))
            {
                Log.writeLog(url + " **Failure** Retrieved something other than HTML");
                return false;
            }
            Elements linksOnPage = htmlDocument.select("a[href]");
            for(Element siteLink : linksOnPage)
            {
                this.links.add(siteLink.absUrl("href"));
            }
            return true;
        }
        catch(Exception e)
        {
            Log.writeLog(e.getMessage());
            return false;
        }
    }

    public List<String> getLinks()
    {
        return this.links;
    }
}
