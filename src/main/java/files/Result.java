package files;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sortedlinks.Link;
import sortedlinks.SortedLinks;

public class Result {

    private final static String FILENAME = "result.txt";

    public static void writeDnsRoutes(List<Link> routes, String dns) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILENAME, true))) {
            pw.write("      " + dns + "(" + routes.size() + ")" + "\r\n");
            for (int i = 0; i < routes.size(); i++) {
                if(routes.get(i).getWithHTTP() != null)
                pw.write(routes.get(i).getWithHTTP() + "\r\n");
            }
            pw.close();
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public static void writeResult(SortedLinks sl, int sitesVisited, int urlsAnalyzed) {
        Map<String, List<Link>> result = sl.get();
        java.util.Date utilDate = new java.util.Date();
        Result.resultClear();
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILENAME, true))) {

            pw.write("Result " + utilDate + "\r\n");
            pw.write("Urls analyzed " + urlsAnalyzed + "\r\n");
            pw.write("Sites visited" + sitesVisited + "\r\n");
            
            pw.close();
            for (Map.Entry<String, List<Link>> entry : result.entrySet()) {
                System.out.println("foreach");
                writeDnsRoutes(entry.getValue(), entry.getKey());
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public static void resultClear() { // clears log 
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILENAME, false))) {
            pw.write("");
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }

    public static void writeLogLine(String str) { // writes to same line whitout newline commands and whitout timestamp
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILENAME, true))) {
            pw.write(str);
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}
