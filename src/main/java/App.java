
import Queryfilter.DnsOnce;
import Queryfilter.RequireSsl;
import Queryfilter.ValidUrl;
import files.Log;
import src.Crawler;


public class App {
    public static void main(String[] args)
    {
        Crawler crawler = new Crawler();
        crawler.addFilter(new ValidUrl());
        crawler.addFilter(new RequireSsl());
        crawler.addFilter(new DnsOnce());
        crawler.crawl();
        Log.writeLog("Crawler ended with status code 0");
        
    }
}
