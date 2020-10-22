package StringsThirdAssignments;

public class Part2
{

    public float cgRatio(String dna)
    {
        if (dna.length() == 0)
        {
            System.out.println("Empty dna");
            return 0;
        }
        int cg = 0;
        for (int i = 0; i < dna.length(); i++)
        {
            if (dna.toUpperCase().charAt(i) == 'C' || dna.toUpperCase().charAt(i) == 'G')
            {
                cg++;
            }
        }
        return (float) cg / dna.length();
    }

    public void testCGRatio(String dna)
    {
        System.out.println("Testing cgRatio with DNA " + dna);
        System.out.println("cgRatio is " + cgRatio(dna));
    }

    public int countCTG(String dna)
    {
        if (dna.length() == 0)
        {
            System.out.println("Empty dna");
            return 0;
        }
        int ctg = 0;

        while (true)
        {
            if (dna.toUpperCase().indexOf("CTG") >= 0)
            {
                ctg++;
                dna=dna.substring(3+dna.toUpperCase().indexOf("CTG"));
            } else
            {
                break;
            }
        }
        return ctg;
    }

    public void testCountCTG(String dna)
    {
        System.out.println("Testing countTCG with DNA: "+dna);
        System.out.println("TCG count: "+ countCTG(dna));
    }


    public static void main(String[] args)
    {
        Part2 test = new Part2();
        test.testCGRatio("ATGCCATAG");
        test.testCountCTG("actctgccgctgtaccaatctcctgtaaaagaattagataaattcaaattagacttagga");
    }
}
