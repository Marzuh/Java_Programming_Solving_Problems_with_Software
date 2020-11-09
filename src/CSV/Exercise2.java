package CSV;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Exercise2
{
    public CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord coldestHour=null;
        for (CSVRecord currentRow : parser)
        {
            if(coldestHour==null)
            {
                coldestHour=currentRow;
            }
            else
            {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(coldestHour.get("TemperatureF"));
                if(currentTemp< lowestTemp)
                {
                    coldestHour=currentRow;
                }
            }
        }
        return coldestHour;
    }

    public void testColdestHourInFile()
    {
        FileResource fr = new FileResource();
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was "+ lowest.get("TemperatureF")+" at "+ lowest.get("TimeEST") );
    }

    

    public static void main(String[] args)
    {
        Exercise2 ex2 = new Exercise2();
        ex2.testColdestHourInFile();
    }
}
