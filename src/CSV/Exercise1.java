package CSV;

import edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

public class Exercise1
{
    public void tester()
    {
        FileResource fr = new FileResource();

        CSVParser parser = fr.getCSVParser();
        System.out.println("Country info about Nauru");
        System.out.println(countryInfo(parser, "Nauru"));

        System.out.println();
        parser = fr.getCSVParser();
        System.out.println("Countries exports fish and nuts");
        listExportersTwoProducts(parser, "fish", "nuts");

        System.out.println();
        parser = fr.getCSVParser();
        System.out.println("Amount of sugar exporters is: ");
        System.out.println(numberOfExporters(parser, "sugar"));

        System.out.println();
        parser = fr.getCSVParser();
        System.out.println("Econmies bigger than $999,999,999,999");
        bigExporters(parser, "$999,999,999,999");

    }

    public String countryInfo(CSVParser parser, String country)
    {
        String result = "NOT FOUND";
        for (CSVRecord record : parser)
        {
            if (record.get("Country").equals(country))
            {
                String exports = record.get("Exports");
                String value = record.get("Value (dollars)");
                result = country + ": " + exports + ": " + value;
            }
        }

        return result;
    }

    public void listExportersTwoProducts(CSVParser parser, String exportitem1, String exportitem2)
    {
        for (CSVRecord record : parser)
        {
            if (record.get("Exports").contains(exportitem1) && record.get("Exports").contains(exportitem2))
            {
                System.out.println(record.get("Country"));
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportitem)
    {
        int result = 0;
        for (CSVRecord record : parser)
        {
            if (record.get("Exports").contains(exportitem))
            {
                result++;
            }
        }
        return result;
    }

    public void bigExporters(CSVParser parser, String amount)
    {
        for (CSVRecord record : parser)
        {
            if (record.get("Value (dollars)").length() > amount.length())
            {
                System.out.println(record.get("Country") + " " + record.get("Value (dollars)"));
            }
        }
    }

    public static void main(String[] args)
    {
        Exercise1 ex1 = new Exercise1();
        ex1.tester();
    }
}
