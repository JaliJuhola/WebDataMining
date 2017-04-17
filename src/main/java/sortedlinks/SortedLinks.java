/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortedlinks;

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
            List<Link> linkList = this.listOfLinks.get(newLink.getDns());
            for (int i = 0; i < linkList.size(); i++) {
                if (linkList.get(i).getDns().equals(newLink.getDns())) {
                    return false;
                }
            }
            this.linksInList++;
            linkList.add(newLink);
            return true;
        }
    }

    public boolean addToList(String link) {
        return (addToList(new Link(link)));
    }
    public boolean uniqueDns(String link)
    {
        Link l = new Link(link);
        return !this.listOfLinks.containsKey(l.getDns());
    }
    public boolean uniqueRoute(String link)
    {
        Link l = new Link(link);
        if(this.listOfLinks.containsKey(l.getDns()))
        {
            List<Link> routes = this.listOfLinks.get(l.getDns());
            for(int i = 0; i < routes.size(); i++)
            {
                if(routes.get(i).getDns().equals(l.getDns()))
                {
                    return false;
                }
            }
        }
        return true;
    }

}
