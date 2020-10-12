public class part3
{

    boolean twoOccurrences(String a, String b)
    {
        int c= b.indexOf(a);
        if (b.indexOf(a) >= 0)
        {
            if (b.indexOf(a, b.indexOf(a) + 1)>=0)
            {
                return true;
            }
        }
        return false;

    }

    void testing()
    {
        System.out.println("String a= ba, b= banana la de bomba " + twoOccurrences("ba", "banana la de bomba"));
        System.out.println("String a= ba, b= banana la de boom " + twoOccurrences("ba", "banana la de boom"));
    }

    String lastPart(String a, String b)
    {
        String result="";
        if(b.indexOf(a)>=0)
        {
            result = b.substring(b.indexOf(a)+a.length());
        }
        else
        {
            result = b;
        }
        return result;
    }

    public static void main(String[] args)
    {
        part3 test =new part3();
        test.testing();
        System.out.println(test.lastPart("an", "banana"));
        System.out.println(test.lastPart("zoo", "forest"));
    }
}
