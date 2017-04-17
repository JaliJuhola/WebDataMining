
package sitefilter;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HasWords implements SitebodyFilter {
    String[] keywords;
    public HasWords(String[] keywords)
    {
        this.keywords = keywords;
    }

    @Override
    public boolean siteMatches(Document document) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
