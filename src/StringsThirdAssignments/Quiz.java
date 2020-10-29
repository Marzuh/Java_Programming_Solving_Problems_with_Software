package StringsThirdAssignments;

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

        while (true)
        {
            stopIndex = dna.toUpperCase().indexOf(stopCodon, startIndex);

            //если стопКодона не найдено возвращаем -1
            if (stopIndex == -1)
            {
                return -1;
            }

            //если найден стоп кодон и отсутсвует сдвиг относительно стартового кодона, выйти из цикла
            if ((stopIndex - startIndex) % 3 == 0)
            {
                break;
            }

            //если найденное слово не подходит, ищем дальше начиная со следующего слова после предидущего
            startIndex = stopIndex + 3;
        }

        return stopIndex;
    }

    public void testFindStopCodon()
    {
        String dna = "acaagtttgtacaaaaaagcagaagggccgtcaaggcccaccatgcctattggatccaaagagaggccaacattttttgaaatttttaagacacgctgcaacaaagcagatttaggaccaataagtcttaattggtttgaagaactttcttcagaagctccaccctataattctgaacctgcagaagaatctgaacataaaaacaacaattacgaaccaaacctatttaaaactccacaaaggaaaccatcttataatcagctggcttcaactccaataatattcaaagagcaagggctgactctgccgctgtaccaatctcctgtaaaagaattagataaattcaaattagacttaggaaggaatgttcccaatagtagacataaaagtcttcgcacagtgaaaactaaaatggatcaa";
        System.out.println("TAA " + findStopCodon(dna, 42, "TAA"));
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
        if ((stopIndex==-1) || (stopIndex > tagStopIndex) && tagStopIndex != -1)
        {
            stopIndex = tagStopIndex;
        }

        //вставить проверку если стопкодон совсем не найдет, то вернуть пустой стринг
        if(stopIndex==-1)
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

    public StorageResource findAllGenes()
    {
        StorageResource geneList = new StorageResource();
        return geneList;
    }

    public void processGene()
    {

    }

    public static void main(String[] args)
    {
        Quiz test = new Quiz();
        test.testFindStartCodon();
        test.testFindStopCodon();
        test.testFindSingleGene();
    }

}
