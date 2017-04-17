
package Queryfilter;

import sortedlinks.SortedLinks;

public class RequireSsl implements Filter {

    
    @Override
    public int getPriority(String url) {
        if(url.startsWith("https://"))
        {
            return 10;
        } else if(url.startsWith("http://"))
        {
            return 0;
        } else {
            return 3;
        }
    }

    @Override
    public boolean checkIfAllowed(String url) {
        return true;//return url.startsWith("https://");
        
    }

    @Override
    public boolean needsLinkList() {
        return false;
    }

    @Override
    public int getPriority(String url, SortedLinks sl) {
         // Shouldn't be called
         return this.getPriority(url);
    }

    @Override
    public boolean checkIfAllowed(String url, SortedLinks sl) {
        return this.checkIfAllowed(url);
        
    }
    
}
