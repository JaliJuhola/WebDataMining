/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import Queryfilter.Filter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import sortedlinks.Link;
import sortedlinks.SortedLinks;

public class Crawler {

    public static final String DEFAULTSTARTING = "https://www.uta.fi";
    public static final int DEFAULTMAXVISITS = 200;
    private SortedLinks visited;
    private SortedLinks matches;
    private int visitAmount;
    private final int maxVisits;
    private Queue<String> unvisited;
    private String nextVisit;
    private ArrayList<Filter> filters = new ArrayList<>();

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
        if (!this.unvisited.isEmpty() && this.maxVisits > this.visitAmount) {
            boolean goodUrl = false;
            while (!goodUrl) {
                this.nextVisit = this.unvisited.remove();
                if (this.validateWithoutPriority(this.nextVisit) != null) {
                    goodUrl = true;
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
                while (!links.isEmpty()) {
                    this.unvisited.add(links.remove(0));
                }
                this.visitAmount++;
            }
            System.out.println(this.nextVisit);
            this.visited.addToList(new Link(this.nextVisit));
            this.nextUrl();
        }
    }

    public SortedLinks getVisited() {
        return this.visited;
    }

    public void addFilter(Filter filter) {
        this.filters.add(filter);
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
