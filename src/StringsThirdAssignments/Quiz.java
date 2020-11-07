package StringsThirdAssignments;

import edu.duke.FileResource;
import edu.duke.StorageResource;

public class Quiz
{
    public int findStartCodon(String dna)
    {
        int startIndex = dna.toUpperCase().indexOf("ATG");
        return startIndex;
    }

    public void testFindStartCodon()
    {
        String dna = "acaagtttgtacaaaaaagcagaagggccgtcaaggcccaccatgcctattggatccaaagagaggccaacattttttgaaatttttaagacacgctgcaacaaagcagatttaggaccaataagtcttaattggtttgaagaactttcttcagaagctccaccctataattctgaacctgcagaagaatctgaacataaaaacaacaattacgaaccaaacctatttaaaactccacaaaggaaaccatcttataatcagctggcttcaactccaataatattcaaagagcaagggctgactctgccgctgtaccaatctcctgtaaaagaattagataaattcaaattagacttaggaaggaatgttcccaatagtagacataaaagtcttcgcacagtgaaaactaaaatggatcaa";
        System.out.println(findStartCodon(dna));
    }

    public int findStopCodon(String dna, int startIndex, String stopCodon)
    {
        int stopIndex = 0;
        int stopIndexStart = startIndex + 3;
        while (stopIndex != -1)
        {
            stopIndex = dna.toUpperCase().indexOf(stopCodon, stopIndexStart);

            //если найден стоп кодон и отсутсвует сдвиг относительно стартового кодона, выйти из цикла
            if ((stopIndex - startIndex) % 3 == 0)
            {
                return stopIndex;
            }

            //если найденное слово не подходит, ищем дальше начиная со следующего слова после предидущего
            stopIndexStart = stopIndex + 3;
        }

        return -1;
    }

    public void testFindStopCodon()
    {
        //String dna = "acaagtttgtacaaaaaagcagaagggccgtcaaggcccaccatgcctattggatccaaagagaggccaacattttttgaaatttttaagacacgctgcaacaaagcagatttaggaccaataagtcttaattggtttgaagaactttcttcagaagctccaccctataattctgaacctgcagaagaatctgaacataaaaacaacaattacgaaccaaacctatttaaaactccacaaaggaaaccatcttataatcagctggcttcaactccaataatattcaaagagcaagggctgactctgccgctgtaccaatctcctgtaaaagaattagataaattcaaattagacttaggaaggaatgttcccaatagtagacataaaagtcttcgcacagtgaaaactaaaatggatcaa";
        String dna = "aatgabctaa";
        System.out.println("TAA " + findStopCodon(dna, 1, "TAA"));
        System.out.println("TGA " + findStopCodon(dna, 42, "TGA"));
        System.out.println("TAG " + findStopCodon(dna, 42, "TAG"));
        System.out.println("42000 " + findStopCodon(dna, 42000, "TAA"));
        System.out.println("taa " + findStopCodon(dna, 42, "taa"));
    }

    public String findSingleGene(String dna)
    {
        int startIndex = findStartCodon(dna);
        int taaStopIndex = findStopCodon(dna, startIndex, "TAA");
        int tgaStopIndex = findStopCodon(dna, startIndex, "TGA");
        int tagStopIndex = findStopCodon(dna, startIndex, "TAG");

        int stopIndex = taaStopIndex;
        if ((stopIndex == -1) || (stopIndex > tgaStopIndex) && tgaStopIndex != -1)
        {
            stopIndex = tgaStopIndex;
        }
        if ((stopIndex == -1) || (stopIndex > tagStopIndex) && tagStopIndex != -1)
        {
            stopIndex = tagStopIndex;
        }

        if (stopIndex == -1)
        {
            return "";
        }

        return dna.substring(startIndex, stopIndex + 3);
    }

    public void testFindSingleGene()
    {
        String dna = "acaagtttgtacaaaaaagcagaagggccgtcaaggcccaccatgcctattggatccaaagagaggccaacattttttgaaatttttaagacacgctgcaacaaagcagatttaggaccaataagtcttaattggtttgaagaactttcttcagaagctccaccctataattctgaacctgcagaagaatctgaacataaaaacaacaattacgaaccaaacctatttaaaactccacaaaggaaaccatcttataatcagctggcttcaactccaataatattcaaagagcaagggctgactctgccgctgtaccaatctcctgtaaaagaattagataaattcaaattagacttaggaaggaatgttcccaatagtagacataaaagtcttcgcacagtgaaaactaaaatggatcaa";
        System.out.println("single dna: " + findSingleGene(dna));
    }

    public StorageResource findAllGenes(String dna)
    {
        //создаём список, ищем ген, если ген найдет отрезаем дна спереди на длину гена(НБ. не забыть что ген может
        //начинаться не с первого символа!!), повторить, если возвращает пустой ген, то закончить
        StorageResource geneList = new StorageResource();
        String singleGene;
        int i = 1;
        while (true)
        {
            singleGene = findSingleGene(dna);
            //System.out.println(i + " : " + singleGene + " and its length is " + singleGene.length());
            i++;
            if (singleGene == "")
            {
                break;
            }
            geneList.add(singleGene);
            dna = dna.substring(dna.indexOf(singleGene) + singleGene.length());
        }
        return geneList;
    }

    public void testFindAllGenes()
    {
        String dna = "acaagtttgtacaaaaaagcagaagggccgtcaaggcccaccatgcctattggatccaaagagaggccaacattttttgaaatttttaagacacgctgcaacaaagcagatttaggaccaataagtcttaattggtttgaagaactttcttcagaagctccaccctataattctgaacctgcagaagaatctgaacataaaaacaacaattacgaaccaaacctatttaaaactccacaaaggaaaccatcttataatcagctggcttcaactccaataatattcaaagagcaagggctgactctgccgctgtaccaatctcctgtaaaagaattagataaattcaaattagacttaggaaggaatgttcccaatagtagacataaaagtcttcgcacagtgaaaactaaaatggatcaa";
        findAllGenes(dna);
    }

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

    public int ctgCounter(String dna)
    {
        int position=0, counter=0;
        while (true)
        {
            if (dna.toUpperCase().indexOf("CTG") >= 0)
            {
                counter++;
                dna = dna.substring(3 + dna.toUpperCase().indexOf("CTG"));
            } else
            {
                break;
            }
        }
        return counter;
    }

    public void processGene(StorageResource sr) //передадут метод файнАллГенес
    {
        StorageResource geneList = new StorageResource();
        for (String g : sr.data())
        {
            geneList = findAllGenes(g);
            System.out.println("CTG codon counter = "+ctgCounter(g));
        }
        int dnaCounter = 0, longDnaCounter = 0, cgRatioCounter = 0, longestLength=0;
        for (String gene : geneList.data())
        {
            dnaCounter++;
            if(longestLength<gene.length())
            {
                longestLength=gene.length();
            }

            if (gene.length() > 60)
            {
                longDnaCounter++;
            }

            if (cgRatio(gene) > 0.35)
            {
                cgRatioCounter++;
            }

        }
        System.out.println("Genes at all are " + dnaCounter);
        System.out.println("Genes longer than 60 are" + longDnaCounter);
        System.out.println("Genes with high cg ratio are " + cgRatioCounter);
        System.out.println("Longest gene lenght is "+ longestLength);
    }

    public static void main(String[] args)
    {
        Quiz test = new Quiz();
        //test.testFindStartCodon();
        //test.testFindStopCodon();
        //test.testFindSingleGene();
        //test.testFindAllGenes();

        //Reading from file
        FileResource fr = new FileResource("/home/marzuh/IdeaProjects/Java_Programming_Solving_Problems_with_Software/src/StringsThirdAssignments/GRch38dnapart.fa");
        String dna = fr.asString();
        StorageResource store = new StorageResource();
        for (String s : fr.words())
        {
            store.add(s);
        }
        test.processGene(store);
    }

}
