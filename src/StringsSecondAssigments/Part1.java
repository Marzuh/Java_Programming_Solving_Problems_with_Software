package StringsSecondAssigments;


public class Part1
{

    int findStopCodon(String dna, int startIndex, String stopCodon)
    {
        int index= 0;

        while(true)
        {
            //
            index = dna.toUpperCase().indexOf(stopCodon, startIndex + 3);

            //если стопКодон не найден или ??остаток от деления разности индекса и старого индекса равен нулю, то прервать
            if (index == -1 || (index - startIndex) % 3 == 0)
            {
                break;
            }

            startIndex += 3;
        }
        if (index != -1)
        {
            return index;
        }
        else
        {
            return dna.length();
        }


    }

    void testFindStopCodon()
    {
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";

        int index = findStopCodon(dna, 0, "TAA");
        System.out.println("Index 1 = " + index);

        index = findStopCodon(dna, 9, "TAA");
        System.out.println("Index 2 = " + index);

        index = findStopCodon(dna, 1, "TAA");
        System.out.println("Index 3 = " + index);

        index = findStopCodon(dna, 0, "TAG");
        System.out.println("Index 4 = " + index);
    }

    String findGene(String dna)
    {

        int startIndex=dna.toUpperCase().indexOf("ATG");
        if(startIndex==-1)
        {
            return "";
        }

        int stopIndex=findStopCodon(dna, startIndex, "TAA")+3;

        if(stopIndex>findStopCodon(dna,startIndex,"TAG"))
        {
            stopIndex=findStopCodon(dna,startIndex,"TAG")+3;
        }

        if(stopIndex>findStopCodon(dna,startIndex,"TGA"))
        {
            stopIndex=findStopCodon(dna,startIndex,"TGA")+3;
        }

        if(stopIndex==dna.length())
        {
            return "";
        }
        else
        {
            return dna.substring(startIndex,stopIndex);
        }

    }

    void testFindGene()
    {
        String dna = "ATGTAAGATGCCCTAGT";
        System.out.println("DNA: " +findGene(dna));

    }

    void printAllGene(String dna)
    {
        int i=1;
        String gene=findGene(dna);

        while(true)
        {
            if(gene=="")
            {
                break;
            }
            System.out.println("Gene "+i+" "+gene);
            gene=findGene(dna.substring(gene.length()));
            i++;
        }

    }

    public static void main(String[] args)
    {
        Part1 test = new Part1();
        test.testFindStopCodon();
        test.testFindGene();
        String dna="ATGTAAGATGCCCTAGT";
        test.printAllGene(dna);
    }
}
