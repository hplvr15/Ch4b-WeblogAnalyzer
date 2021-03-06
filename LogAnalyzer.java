/**
 * Read web server data and analyse
 * hourly access patterns.
 * 
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;
    private String name[];

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader();
    }
    
    public LogAnalyzer(String filename)
    {
        hourCounts = new int[24];
        reader = new LogfileReader(filename);
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
    
    public int numberofAccesses()
    {
        int total = 0;
        for(int hour = 0; hour < hourCounts.length; hour++) 
        {
            total = total + hourCounts[hour];
        }
            return total;
    }
    
    public int busiHour() 
    {
         int numOfAccessesAtBusi = 0;
         int busiHour = 0;
         int index = 0;
         
         while (index < hourCounts.length -1) 
         {
             if (numOfAccessesAtBusi < hourCounts[index]) 
             {
                 busiHour = index;
                 numOfAccessesAtBusi = hourCounts[index];
                 index++;
             }
             else 
             {
                 index++;
             }
         }
         return busiHour;
     }
    
      
   public int twoHourBusi()
   {
       int numOfAccessesAtBusi = 0;
       int index = 0;
       int busiHour = 0;
                       
     	while(index < hourCounts.length - 1)
     	{
             if (numOfAccessesAtBusi < hourCounts[index] + hourCounts[index + 1])
             {
                  busiHour = index;
                  numOfAccessesAtBusi = hourCounts[index] + hourCounts[index + 1];
                  index++;
             }
             else 
             {
                 index++;
             }
        }
            return busiHour;
    }        

    public int quietetHour() 
    {
        int numOfAccessesAtQuiete = 9999;
        int quieteHour = 0;
        int index = 0;
        
        while (index < hourCounts.length -1) 
        {
             if (numOfAccessesAtQuiete > hourCounts[index])
            {
                 quieteHour = index;
                 numOfAccessesAtQuiete = hourCounts[index];
                index++;
            }
             else 
             {
                 index++;
             }
         }
         return quieteHour;
     }
     
     
}
