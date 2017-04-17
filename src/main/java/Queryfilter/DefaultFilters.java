package Queryfilter;

import files.Log;
import sortedlinks.Link;
import sortedlinks.SortedLinks;

/**
 *
 * @author JJ
 */
public class DefaultFilters implements Filter {

    @Override
    public int getPriority(String url) {
        Log.writeLog("getPriority called in RouteOnce without SortedLinks");
        return 0;
    }

    @Override
    public boolean checkIfAllowed(String url) {
        Log.writeLog("checkifAllowed called in RouteOnce without SortedLinks");
        return false;
    }

    @Override
    public boolean needsLinkList() {
        return true;
    }

    @Override
    public int getPriority(String url, SortedLinks sl) {
        if (sl.uniqueRoute(url)) {
            return 100;
        }
        if (sl.uniqueDns(url)) {
            return 20;
        }
        return 2;
    }

    @Override
    public boolean checkIfAllowed(String url, SortedLinks sl) {
        Link l = new Link(url);
        if(l.getRoutes()[0] == null)
        {
            if(!sl.uniqueDns(url))
            {
                return false;
            }
        }
        return true;
    }
}
