
import Queryfilter.DefaultFilters;
import Queryfilter.DnsOnce;
import Queryfilter.FirstRouteOnce;
import Queryfilter.RequireSsl;
import Queryfilter.RouteOnce;
import Queryfilter.ValidUrl;
import files.Log;
import sitefilter.Metalanguage;
import src.Crawler;


public class App {
    public static void main(String[] args)
    {
        Log.logClear();
        Crawler crawler = new Crawler();
        crawler.addFilter(new DefaultFilters());
        crawler.addFilter(new RouteOnce());
        crawler.addFilter(new ValidUrl());
        crawler.addFilter(new FirstRouteOnce());
        crawler.addSitebodyFilter(new Metalanguage("fi"));
        
        crawler.crawl();
        
        Log.writeLog("Crawler ended with status code 0");
        
    }
}
