/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortedlinks;

import files.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author JJ
 */
public class SortedLinks {

    private Map<String, List<Link>> listOfLinks;
    private int uniqueDns;
    private int linksInList;

    public SortedLinks() {
        this.listOfLinks = new HashMap<String, List<Link>>();
        this.uniqueDns = 0;
        this.linksInList = 0;
    }

    public SortedLinks(String[] links) {
        this.listOfLinks = new HashMap<String, List<Link>>();
        this.uniqueDns = 0;
        this.linksInList = 0;
        for (int i = 0; i < links.length; i++) {
            Link newLink = new Link(links[i]);
            if (!this.listOfLinks.containsKey(newLink.getDns())) {
                this.uniqueDns++;
                ArrayList<Link> list = new ArrayList<>();
                list.add(newLink);
                this.listOfLinks.put(newLink.getDns(), list);
            } else {
                this.listOfLinks.get(newLink.getDns()).add(newLink);
            }
        }
    }

    public int amountUniqueDns() {
        return this.uniqueDns;
    }

    public int amountUniqueLinks() {
        return this.linksInList;
    }

    public boolean addToList(Link newLink) {
        if (!this.listOfLinks.containsKey(newLink.getDns())) {
            this.uniqueDns++;
            ArrayList<Link> list = new ArrayList<>();
            list.add(newLink);
            this.listOfLinks.put(newLink.getDns(), list);
            this.linksInList++;
            return true;
        } else {
            this.listOfLinks.get(newLink.getDns()).add(newLink);
            this.linksInList++;
            return true;
        }
    }

    public boolean addToList(String link) {
        return (addToList(new Link(link)));
    }

    public boolean uniqueDns(String link) {
        Link l = new Link(link);
        String[] dns = l.getDns().split("#");
        List<Link> items = this.listOfLinks.get(dns[0]);
        return items == null;
    }

    public boolean uniqueRoute(String link) {
        Link l = new Link(link);
        if (this.listOfLinks.containsKey(l.getDns())) {
            List<Link> routes = this.listOfLinks.get(l.getDns());
            for (int i = 0; i < routes.size(); i++) {
                if (routes.get(i).getWhitoutHTTP().equals(l.getWhitoutHTTP())) {
                    return false;
                }
            }
        }
        return true;
    }

    public Map<String, List<Link>> get() {
        return this.listOfLinks;
    }

    public boolean uniqueFirstRoute(String link) {
        Link l = new Link(link);
        if (this.listOfLinks.containsKey(l.getDns())) {
            List<Link> routes = this.listOfLinks.get(l.getDns());
            for (int i = 0; i < routes.size(); i++) {
                if (routes.get(i).getRoutes()[0] != null && l.getRoutes()[0] != null) {
                    if (routes.get(i).getRoutes()[0].equals(l.getRoutes()[0])) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
