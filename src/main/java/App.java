
import files.Log;
import src.Crawler;


public class App {
    public static void main(String[] args)
    {
        Crawler crawler = new Crawler();
        crawler.crawl();
        Log.writeLog("Crawler ended with status code 0");
        
    }
}
