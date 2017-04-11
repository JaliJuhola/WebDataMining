
package files;


import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
// Debug information
public class Log { 

    private final static String FILENAME = "log.txt";
    
    public static void writeLog(String logText) { // Writes timestamp to beginning of line and also newline end of line.
        java.util.Date utilDate = new java.util.Date();
        java.sql.Timestamp sq = new java.sql.Timestamp(utilDate.getTime());
        String s = sq + ":  " + logText;
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILENAME, true))) {
            pw.write(s + "\r\n");
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }

    public static void logClear() { // clears log 
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