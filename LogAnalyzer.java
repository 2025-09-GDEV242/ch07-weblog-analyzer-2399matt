import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version    2016.02.29
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     * Param fileName - name of the file to be analyzed.
     */
    public LogAnalyzer(String fileName)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(fileName);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
    
    /**
     * Returns the total number of accesses.
     * @return The total number of accesses from the log file.
     */
    public int numberOfAccesses() {
        int total = 0;
        for(int i = 0; i < hourCounts.length; i++) {
            total += hourCounts[i];
        }
        return total;
    }
    
    /**
     * Returns the hour with the highest number of accesses. 
     * @return the hour with the most number of accesses.
     */
    public int busiestHour() {
        int maxIndex = 0;
        int temp = hourCounts[0];
        for(int i = 0; i < hourCounts.length; i++) {
           if(hourCounts[i] > temp) {
               maxIndex = i;
           }
        }
        return maxIndex;
    }
    
    /**
     * Returns the hour with the least number of accesses.
     * @return the hour with the least number of accesses.
     */
    public int quitestHour() {
        int minIndex = 0;
        int temp = hourCounts[0];
        for(int i = 0; i < hourCounts.length; i++) {
            if(hourCounts[i] < temp) {
                minIndex = i;
            }
        }
        return minIndex;
    }
    
    /**
     * Returns the first hour of the two-hour range that has the most number of
     * accesses.
     * @return The first hour of the two-hour range with the most accesses.
     */
    public int getBusiestTwoHours() {
        int temp = 0;
        int twoHourStartIndex = 0;
        for(int i = 0; i < hourCounts.length-1; i++) {
            if(hourCounts[i] + hourCounts[i+1] > temp) {
                temp = hourCounts[i] + hourCounts[i+1];
                twoHourStartIndex = i;
            }
        }
        return twoHourStartIndex;
        
    }
    
    /**
     * Returns the day with the most number of accesses.
     * @return The day with the most number of accesses.
     */
    public int busiestDay() {
       reader.reset(); 
       int[] days = new int[32];
       while(reader.hasNext()) {
           LogEntry entry = reader.next();
           days[entry.getDay()]++;
       }
       int maxIndex = 0;
       int max = 0;
       for(int i = 0; i < days.length; i++) {
           if(days[i] > max) {
               max = days[i];
               maxIndex = i;
           }
       }
       return maxIndex;
    }
    
    /**
     * Returns the LogfileReader object. Use for testing.
     */
    public LogfileReader getReader() {
        return this.reader;
    }
}
