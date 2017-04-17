package sitefilter;

import org.jsoup.nodes.Document;

/**
 *
 * @author JJ
 */
public interface SitebodyFilter {
    public boolean siteMatches(Document document);
}
