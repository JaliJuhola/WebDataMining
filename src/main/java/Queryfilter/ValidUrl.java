package Queryfilter;

import files.Log;
import sortedlinks.Link;
import sortedlinks.SortedLinks;

public class ValidUrl implements Filter {

    @Override
    public int getPriority(String url) {
        if(checkIfAllowed(url))
        {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public boolean checkIfAllowed(String url) {
        if (url.startsWith(Link.WITHSSL)) {
            url = url.replaceFirst(Link.WITHSSL, "");
        } else if (url.startsWith(Link.WITHOUTSSL)) {
            url = url.replaceFirst(Link.WITHSSL, "");
        }
        for (int i = 0; i < url.length(); i++) {
            if (url.charAt(i) == '.') {
                if (url.endsWith(".")) {
                    return false;
                }
                break;
            }

        }
        return true;
    }

    @Override
    public boolean needsLinkList() {
        return false;
    }

    @Override
    public int getPriority(String url, SortedLinks sl) {
               Log.writeLog("getPriority() with sortedLinks called with " + url);
       return this.getPriority(url);
    }

    @Override
    public boolean checkIfAllowed(String url, SortedLinks sl) {
       Log.writeLog("checkifAllowed() with sortedLinks called with " + url);
        return this.checkIfAllowed(url);
    }
}
