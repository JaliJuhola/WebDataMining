/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import Queryfilter.Filter;
import files.Log;
import files.Result;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.jsoup.nodes.Document;
import sitefilter.SitebodyFilter;
import sortedlinks.Link;
import sortedlinks.SortedLinks;

public class Crawler {
    
    public static final String DEFAULTSTARTING = "http://kiekko.tk";
    public static final int DEFAULTMAXVISITS = 100;
    private SortedLinks visited;
    private SortedLinks matches = new SortedLinks();
    private int visitAmount;
    private final int maxVisits;
    private Queue<String> unvisited;
    private String nextVisit;
    private ArrayList<Filter> filters = new ArrayList<>();
    private ArrayList<SitebodyFilter> sitebodyFilters = new ArrayList<>();
    
    public Crawler() {
        this.visited = new SortedLinks();
        this.unvisited = new LinkedList<>();
        this.maxVisits = Crawler.DEFAULTMAXVISITS;
        this.nextVisit = Crawler.DEFAULTSTARTING;
        this.visitAmount = 0;
        
    }
    
    public Crawler(String startingPoint, int maxVisits) {
        this.unvisited = new LinkedList<>();
        this.visited = new SortedLinks();
        this.nextVisit = startingPoint;
        this.maxVisits = maxVisits;
        this.visitAmount = 0;
    }
    
    public void nextUrl() {
        if (this.maxVisits <= this.visitAmount) {
            this.nextVisit = null;
            return;
        }
        boolean goodUrl = false;
        while (!goodUrl) {
            if (this.unvisited.isEmpty()) {
                this.nextVisit = null;
                return;
            }
            this.nextVisit = this.unvisited.remove();
            if (this.validateWithoutPriority(this.nextVisit) != null) {
                if (this.matches.uniqueDns(this.nextVisit)) {
                    goodUrl = true;
                    return;
                }
            }
        }
        
    }

    /* Main method which runs crawling */
    public void crawl() {
        while (this.nextVisit != null) {
            SiteVisitor visit = new SiteVisitor();
            if (visit.visit(new Link(this.nextVisit))) {
                List<String> links = visit.getLinks();
                if (this.checkSiteFilters(visit.getDocument())) {
                    System.out.println(this.nextVisit);
                    if (this.matches.uniqueDns(nextVisit)) {
                        this.matches.addToList(new Link(this.nextVisit));
                    } else {
                        Log.writeLog("error adding matches match url already exists!");
                    }
                }
                while (!links.isEmpty()) {
                        
                        this.unvisited.add(links.remove(0));
                }
                this.visitAmount++;
            }
            this.visited.addToList(new Link(this.nextVisit));
            this.nextUrl();
        }
        Result.writeResult(this.visited);
    }
    
    public SortedLinks getVisited() {
        return this.visited;
    }
    
    public void addFilter(Filter filter) {
        this.filters.add(filter);
    }
    
    public void addSitebodyFilter(SitebodyFilter sbf) {
        this.sitebodyFilters.add(sbf);
    }
    
    public boolean checkSiteFilters(Document document) {
        for (int i = 0; i < this.sitebodyFilters.size(); i++) {
            if (!this.sitebodyFilters.get(i).siteMatches(document)) {
                return false;
            }
        }
        return true;
    }
    
    public Link validateWithoutPriority(String url) {
        for (int i = 0; i < this.filters.size(); i++) {
            if (this.filters.get(i).needsLinkList()) {
                if (!this.filters.get(i).checkIfAllowed(url, this.visited)) {
                    return null;
                }
            } else {
                if (!this.filters.get(i).checkIfAllowed(url)) {
                    return null;
                }
            }
        }
        return new Link(url);
    }
}
