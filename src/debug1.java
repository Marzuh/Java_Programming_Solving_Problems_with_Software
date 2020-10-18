public class debug1
{
    public void findAbc(String input)
    {
        int index = input.indexOf("abc");
        while (true)
        {
            //if (index == -1 || index > input.length()-4)
            if(index == -1)
            {
                break;
            }
            //System.out.println("index before "+index);
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+3);
            //System.out.println("index after " + index);
        }
    }
    public void test()
    {
        //no code yet
        //findAbc("abcbbbabcdddabc");
        findAbc("“abcabcabcabca”");
    }

    public static void main(String[] args)
    {
        debug1 debug = new debug1();
        debug.test();
    }
}
