package sitefilter;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Metalanguage implements SitebodyFilter {

    private String language;

    public Metalanguage(String language) {
        this.language = language;
    }

    @Override
    public boolean siteMatches(Document document) {
        if (document.getElementsByTag("html") != null) {
            String language = document.child(0).attr("lang");
            return language.contains(this.language.toUpperCase()) || language.contains(this.language.toLowerCase());
        }
        return false;
    }

}
