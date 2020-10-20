package StringsSecondAssigments;

public class Part2
{
    public int howMany(String stringa, String stringb )
    {
        int counter=0;
        int startIndex = stringb.indexOf(stringa);
        while (startIndex != -1)
        {
            counter++;
            startIndex = stringb.indexOf(stringa,startIndex+stringa.length());

        }

        return counter;
    }

    public void testHowMany()
    {
        System.out.println("Must be 3, function answer is " +howMany("GAA","ATGAACGAATTGAATC"));
        System.out.println("Must be 2, function answer is " +howMany("AA","ATAAAA"));

    }

    public static void main(String[] args)
    {
        Part2 part2= new Part2();
        part2.testHowMany();
    }
}
