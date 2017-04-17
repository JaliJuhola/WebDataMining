/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortedlinks;

public class Link {

    private String wholeLink;
    private String linkDns;
    private String[] linkRoutes;
    private int lastRoute;
    private boolean containsSsl;
    private String queryString;
    public final static String WITHSSL = "https://";
    public final static String WITHOUTSSL = "http://";

    public Link(String url) {
        if (url.startsWith(WITHSSL)) {
            url = url.replace(WITHSSL, "");
            containsSsl = true;
        } else if (url.startsWith(WITHOUTSSL)) {
            url = url.replace(WITHOUTSSL, "");
            containsSsl = false;
        } else {
            containsSsl = false;
        }

        this.wholeLink = url;
        String parts[] = url.split("/");
        linkRoutes = new String[20];
        this.linkDns = parts[0];
        if (parts.length > 1) // link has additional routes.
        {
            int i = 1;
            int e = 1;
            if (parts[1].contains("?")) {
                i++;
                this.queryString = parts[1];
                e++;
            }
            for (; i < parts.length; i++) {
                if (i - e < 20) {
                    linkRoutes[i - e] = parts[i];
                }
            }

        }
    }

    public String getWhitoutHTTP() {
        return this.wholeLink;
    }

    public String getWithHTTP() {
        return this.getSSLPrefix() + this.wholeLink;
    }

    public String getDns() {
        return this.linkDns;
    }

    public String[] getRoutes() {
        return this.linkRoutes;
    }

    public boolean hasSSL() {
        return this.containsSsl;
    }

    public boolean hasQueryString() {
        return (this.queryString == null);
    }

    public String getQueryString() {
        return this.queryString;
    }

    public String getSSLPrefix() {
        if (this.containsSsl) {
            return WITHSSL;
        }
        return WITHOUTSSL;

    }

    public boolean routeStartsWith(String routeStartsWith) {
        if (routeStartsWith.startsWith("/")) {
            {
                routeStartsWith.replaceFirst("/", "");
            }
            String[] parts = routeStartsWith.split("/");
            for (int i = 0; i < parts.length; i++) {
                if (i == 0 && parts[i].contains("?")) {
                    if (!this.queryString.equals(parts[0])) {
                        return false;
                    }
                } else if (!parts[i].equals(this.linkRoutes[i])) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean dnsMatches(String comparedDns) {
        return (this.linkDns.equals(comparedDns));
    }

}
