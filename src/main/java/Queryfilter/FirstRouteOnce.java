/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Queryfilter;

import files.Log;
import sortedlinks.SortedLinks;

public class FirstRouteOnce implements Filter {

    @Override
    public int getPriority(String url) {
        Log.writeLog("getPriority called in DnsOnce without SortedLinks");
        return 0;
    }

    @Override
    public boolean checkIfAllowed(String url) {
        Log.writeLog("checkifAllowed called in DnsOnce without SortedLinks");
        return false;
    }

    @Override
    public boolean needsLinkList() {
        return true;
    }

    @Override
    public int getPriority(String url, SortedLinks sl) {
        if(sl.uniqueRoute(url))
        {
            return 100;
        }
        if(sl.uniqueDns(url))
        {
            return 20;
        }
        return 2;
    }

    @Override
    public boolean checkIfAllowed(String url, SortedLinks sl) {
        return sl.uniqueFirstRoute(url);
    }

}
