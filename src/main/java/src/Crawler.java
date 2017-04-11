/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import sortedlinks.Link;
import sortedlinks.SortedLinks;

public class Crawler {

    public static final String DEFAULTSTARTING = "https://www.uta.fi";
    public static final int DEFAULTMAXVISITS = 10000;
    private SortedLinks visited;
    private final int maxVisits;
    private Queue<String> unvisited;
    private String nextVisit;

    public Crawler() {
        this.visited = new SortedLinks();
        this.unvisited = new LinkedList<>();
        this.maxVisits = Crawler.DEFAULTMAXVISITS;
        this.nextVisit = Crawler.DEFAULTSTARTING;

    }

    public Crawler(String startingPoint, int maxVisits) {
        this.unvisited = new LinkedList<>();
        this.visited = new SortedLinks();
        this.nextVisit = startingPoint;
        this.maxVisits = maxVisits;
    }

    public void nextUrl() {
        if (!this.unvisited.isEmpty()) {
            boolean uniqueLink = false;
            while (!uniqueLink) {
                this.nextVisit = this.unvisited.remove();
                uniqueLink = this.visited.addToList(this.nextVisit);
            }
        } else {
            this.nextVisit = null;
        }
    }

    /* Main method which runs crawling */
    public void crawl() {
        int visitedAmount = 0;
        while (visitedAmount < this.maxVisits && this.nextVisit != null) {
            VisitSite visit = new VisitSite();
            if(visit.visit(new Link(this.nextVisit))) {
            List<String> links = visit.getLinks();
            while (!links.isEmpty()) {
                this.unvisited.add(links.remove(0));
            }
            visitedAmount++;
            }
            this.nextUrl();
        }
    }

    public Queue<String> getUnvisited() {
        return this.unvisited;
    }

    public SortedLinks getVisited() {
        return this.visited;
    }
}
