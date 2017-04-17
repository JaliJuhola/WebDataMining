package Queryfilter;

import sortedlinks.Link;
import sortedlinks.SortedLinks;

public interface Filter {

    public int getPriority(String url);

    public boolean checkIfAllowed(String url);

    public boolean needsLinkList();

    public int getPriority(String url, SortedLinks sl);

    public boolean checkIfAllowed(String url, SortedLinks sl);
}
