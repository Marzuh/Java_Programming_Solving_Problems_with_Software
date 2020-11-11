package CSV;

import edu.duke.DirectoryResource;
import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;

public class Exercise2
{
    public CSVRecord coldestHourInFile(CSVParser parser)
    {
        CSVRecord coldestHour = null;
        for (CSVRecord currentRow : parser)
        {
            if (Double.parseDouble(currentRow.get("TemperatureF")) == -9999)
            {
                break;
            } else
            {
                if (coldestHour == null)
                {
                    coldestHour = currentRow;
                } else
                {
                    double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                    double lowestTemp = Double.parseDouble(coldestHour.get("TemperatureF"));
                    if (currentTemp < lowestTemp)
                    {
                        coldestHour = currentRow;
                    }
                }
            }
        }
        return coldestHour;
    }

    public String fileWithColdestTemperature()
    {
        String fileName = "";
        CSVRecord coldestHour = null;
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInFile(fr.getCSVParser());
            if (coldestHour == null)
            {
                coldestHour = currentRow;
                fileName = f.getName();
            } else
            {
                double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(coldestHour.get("TemperatureF"));
                if (currentTemp < lowestTemp)
                {
                    coldestHour = currentRow;
                    fileName = f.getName();
                }
            }
        }
        return fileName;
    }

    public void testFileWithColdestTemperature()
    {
        System.out.println("Coldest day was in file " + fileWithColdestTemperature());
        FileResource fr = new FileResource("/home/marzuh/study/duke_univercity/nc_weather/2014/" + fileWithColdestTemperature());
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + lowest.get("TemperatureF") + " at " + lowest.get("TimeEST"));
    }

    public void testColdestHourInFile()
    {
        FileResource fr = new FileResource();
        CSVRecord lowest = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest temperature was " + lowest.get("TemperatureF") + " at " + lowest.get("TimeEST"));
    }


    public CSVRecord lowestHumidityInFile(CSVParser parser)
    {
        CSVRecord lowestHumidity = null;
        for (CSVRecord currentRow : parser)
        {
            if (currentRow.equals("N/A") || currentRow.equals(""))//isNumeric
            {
                break;
            } else
            {
                if (lowestHumidity == null)
                {
                    lowestHumidity = currentRow;
                } else
                {
                    double currentHum = Double.parseDouble(currentRow.get("Humidity"));
                    double lowestHum = Double.parseDouble(lowestHumidity.get("Humidity"));
                    if (currentHum < lowestHum)
                    {
                        lowestHumidity = currentRow;
                    }
                }
            }

        }
        return lowestHumidity;
    }

    public CSVRecord lowestHumidityInManyFiles()
    {
        CSVRecord lowestHumidity = null;
        DirectoryResource dr = new DirectoryResource();

        for (File f : dr.selectedFiles())
        {
            FileResource fr = new FileResource(f);

            for (CSVRecord currentRow : fr.getCSVParser())
            {
                if (currentRow.equals("N/A") || currentRow.equals(""))//isNumeric
                {
                    break;
                } else
                {
                    if (lowestHumidity == null)
                    {
                        lowestHumidity = currentRow;
                    } else
                    {
                        double currentHum = Double.parseDouble(currentRow.get("Humidity"));
                        double lowestHum = Double.parseDouble(lowestHumidity.get("Humidity"));
                        if (currentHum < lowestHum)
                        {
                            lowestHumidity = currentRow;
                        }
                    }
                }

            }

        }
        return lowestHumidity;
    }

    public void testLowestHumidityInManyFiles()
    {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println("Lowest humidity is "+record.get("Humidity")+" at "+record.get("DateUTC"));
    }

    public void testLowestHumidityInFile()
    {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println(csv.get("Humidity"));
    }

    public static void main(String[] args)
    {
        Exercise2 ex2 = new Exercise2();
        //ex2.testColdestHourInFile();
        //ex2.testFileWithColdestTemperature();
        //ex2.testLowestHumidityInFile();
        ex2.testLowestHumidityInManyFiles();
    }
}
