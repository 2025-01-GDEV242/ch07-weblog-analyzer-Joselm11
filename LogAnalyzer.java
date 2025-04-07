/**
 * Read web server data and analyse hourly access patterns.
 * 
 * @author Jose Moreno
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
     */
    public LogAnalyzer(String filename)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        reader.reset();
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
     * loops until number of accesses of log file are recorded
     * 
     * @return total number of accesses
     */
    public int numberOfAccesses()
    {
        int total = 0;
        for (int count : hourCounts) {
            total += count;
        }
        return total;
    }
    
    
    /**
     * Hour with the highest accesses
     * 
     * @return busiest hour
     */
    public int busiestHour()
    {
        int maxHour = 0;
        for (int i = 1; i < hourCounts.length; i++) {
            if (hourCounts[i] > hourCounts[maxHour]) {
                maxHour = i;
            }
        }
        return maxHour;
    }

    
    /**
     * hour with the lowest accesses
     * 
     * @return the quietest hour
     */
    public int quietestHour()
    {
        int minHour = 0;
        for (int i = 1; i < hourCounts.length; i++) {
            if (hourCounts[i] < hourCounts[minHour]) {
                minHour = i;
            }
        }
        return minHour;
    }

    
    /**
     * determine the starting of the busiest two hour period
     * cycles through consecutive pairs
     * 
     * @return the start hour of busiest two hour period
     */
    public int busiestTwoHourPeriod()
    {
        int maxSum = 0;
        int maxStartHour = 0;
        for (int i = 0; i < hourCounts.length - 1; i++) {
            int sum = hourCounts[i] + hourCounts[i + 1];
            if (sum > maxSum) {
                maxSum = sum;
                maxStartHour = i;
            }
        }
        return maxStartHour;
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
