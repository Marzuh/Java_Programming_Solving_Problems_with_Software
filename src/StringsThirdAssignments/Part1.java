package StringsThirdAssignments;

import edu.duke.StorageResource;

public class Part1
{
    int findStopCodon(String dna, int startIndex, String stopCodon)
    {
        int stopIndex = 0;

        while (true)
        {
            //
            stopIndex = dna.toUpperCase().indexOf(stopCodon, startIndex);

            //если стопКодон не найден или найденое слово укладывается в 1 кодон, а не находится в 2ух разных
            //на данный момент когда стринга заканчивается, получается бяка, надо разбить на два иф. и подумать как закончить цикл
            if (stopIndex == -1 || (stopIndex - startIndex) % 3 == 0)
            {
                break;
            }

            startIndex = stopIndex + 3;
        }
        if (stopIndex != -1)
        {
            return stopIndex;
        } else
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

    public String findGene(String dna)
    {

        int startIndex = dna.toUpperCase().indexOf("ATG");
        if (startIndex == -1)
        {
            return "";
        }

        int taaStopIndex = findStopCodon(dna, startIndex, "TAA");
        int tagStopIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaStopIndex = findStopCodon(dna, startIndex, "TGA");

        //добавляем + 3 для того что бы конец стринга совпадал с концом гена
        int stopIndex = taaStopIndex + 3;

        if (stopIndex > tagStopIndex)
        {
            stopIndex = tagStopIndex + 3;
        }

        if (stopIndex > tgaStopIndex)
        {
            stopIndex = tgaStopIndex + 3;
        }

        if (stopIndex == dna.length())
        {
            return "";
        }
        else
        {
            System.out.println(dna.substring(startIndex, stopIndex));
            return dna.substring(startIndex, stopIndex);
        }

    }

    void testFindGene()
    {
        String dna = "ATGTAAGATGCCCTAGT";
        System.out.println("DNA: " + findGene(dna));

    }

    void printAllGene(String dna)
    {
        int i = 1;
        String gene = findGene(dna);

        while (true)
        {
            if (gene == "")
            {
                break;
            }
            System.out.println("Gene " + i + " " + gene);
            gene = findGene(dna.substring(gene.length()));
            i++;
        }

    }


    public StorageResource findAllGenes(String dna)
    {
        StorageResource geneList = new StorageResource();
        int i = 1;
        String gene = findGene(dna);
        while (true)
        {
            if (gene == "")
            {
                break;
            }
            if(i==418)
            {
                System.out.println(i);
            }
            geneList.add(gene);
            dna = dna.substring(gene.length());
            if(i==418)
            {
                System.out.println(i);
            }
            gene = findGene(dna);
            i++;
        }
        return geneList;
    }

    public void testFindAllGenes(String dna)
    {
        System.out.println("Testing findAllGenes with DNA: " + dna);
        StorageResource genes = findAllGenes(dna);
        for (String g : genes.data())
        {
            System.out.println(g);
        }
    }

    public static void main(String[] args)
    {
        Part1 test = new Part1();

        String dna = "ATGATCTAATTTATGCTGCAACGGTGAAGA";
        test.testFindAllGenes(dna);
        dna = "PJLJIGKGLHGMFJJI";
        test.testFindAllGenes(dna);
    }
}
