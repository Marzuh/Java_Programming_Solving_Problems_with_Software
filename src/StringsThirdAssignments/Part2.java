package StringsThirdAssignments;

import edu.duke.FileResource;
import edu.duke.StorageResource;

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
                dna = dna.substring(3 + dna.toUpperCase().indexOf("CTG"));
            } else
            {
                break;
            }
        }
        return ctg;
    }

    public void testCountCTG(String dna)
    {
        System.out.println("Testing countTCG with DNA: " + dna);
        System.out.println("TCG count: " + countCTG(dna));
    }

    //передаётся набор стрингов(по факту  1 стринга), в каждой стринге может быть несколько генов которые нужно найти и "вырезеть"
    public void processGenes(StorageResource sr)
    {
        //инстанс Парт1 что бы можно было вызвать метод файнАллГенес()
        Part1 tempPart1 = new Part1();
        int longDnaCounter=0;
        int cgRatioCounter=0;
        String longestGene="";
        //создаётся экземпляр генеЛист где будут сохранены гены
        StorageResource geneList;
        //построчно читаем ср
        for (String g : sr.data())
        {
            //добавляем в генЛист новые гены
            //стринг г читается хорошо
            //System.out.println(g);
            geneList =  tempPart1.findAllGenes(g);
            //System.out.println("gene list is ");
            for(String gene : geneList.data())
            {

                if(gene.length()>9)
                {
                    System.out.print("The gene length is "+gene.length()+" and gene: ");
                    System.out.println(gene);
                    longDnaCounter++;
                    System.out.println("The counter of genes longer than 9 is "+longDnaCounter);
                }

                if(cgRatio(gene)>0.35)
                {
                    System.out.println("Gene with cg ratio higher than 0.35 is "+gene);
                    cgRatioCounter++;
                    System.out.println("The counter of genes with cg ratio higher than 0.35 is "+cgRatioCounter);
                }

                if(gene.length()>longestGene.length())
                {
                    longestGene=gene;
                }

            }
            System.out.println("The longest gene length is "+longestGene.length());
        }
    }

    public static void main(String[] args)
    {
        //тут работает
        Part2 test = new Part2();
        test.testCGRatio("ATGCCATAG");
        test.testCountCTG("actctgccgctgtaccaatctcctgtaaaagaattagataaattcaaattagacttagga");
        //тут не работает
        FileResource fr = new FileResource("/home/marzuh/IdeaProjects/Java_Programming_Solving_Problems_with_Software/src/StringsThirdAssignments/brca1line.fa");
        String dna = fr.asString();
        StorageResource store = new StorageResource();
        for (String s : fr.words())
        {
            store.add(s);
        }
        test.processGenes(store);
    }
}
