import java.util.*;
import java.math.*;

class Splitting
{
    static String[] splitting(String str, String separ)
    {
        if(separ.length() > 1)
        {
            StringTokenizer tokenizer = new StringTokenizer(str, separ);
            String[] strs = new String[tokenizer.countTokens()];
            int i = 0;
            while(tokenizer.hasMoreTokens())
            {
                strs[i] = tokenizer.nextToken();
                i++;
            }
            return strs;
        }
        else
        {
            String[] strs = str.split(separ);
            return strs;
        }
    }
}

public class NewClass {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input string 1");
        String str = "43 (1/96.,543;: 09utre 98432) yanddnay q8 05 ertytre 5665";//scan.nextLine();
        System.out.println("Input separators");
        String separ = "/.,;: ";//scan.nextLine();
        System.out.println("Input P");
        Integer P = 98;//scan.nextInt();
        String[] strs = Splitting.splitting(str, separ);
        System.out.println("Tokens");
        for(int i = 0; i<strs.length; i++)
        {
            System.out.print(strs[i] + " ");
        }
        int[] mas = Parsing(strs);
        System.out.println("\nNumbers");
        for(int i = 0; i<mas.length; i++)
        {
            String output = String.format("%d ", mas[i]);
            System.out.print(output);
        }
        String[] palindrom = Palindrom(strs);
        System.out.println("\nPalindrom");
        for(int i = 0; i<palindrom.length; i++)
        {
            System.out.print(palindrom[i]);
        }
        int index = search(strs, P);
        System.out.println("\nPosition P in string");
        System.out.println(String.format("%d", index));
        String[] strsNew = new String[strs.length+1];
        if(index!=-1)
        {
            for(int i = 0; i<index; i++)
            {
                strsNew[i] = strs[i];
            }
            Double d = Math.random()*100;
            strsNew[index] = Double.toString(d);
            for(int i = index; i<strs.length; i++)
            {
                strsNew[i+1] = strs[i];
            }
        }
        else
        {
            Double d = Math.random()*100;
            strsNew[0] = Double.toString(d);
            for(int i = 0; i<strs.length; i++)
            {
                strsNew[i+1] = strs[i];
            }
        }
        System.out.println("The new string");
        for(int i = 0; i<strsNew.length; i++)
        {
            System.out.print(strsNew[i] + " ");
        }
        System.out.println();
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i<strsNew.length; i++)
        {
            sb.append(strsNew[i]+" ");
        }
        String strNew = sb.toString();
        strNew = dltstaples(strNew);
        System.out.println("Delete staples");
        System.out.println(strNew);
    }


    static int[] Parsing(String[] strs)
    {
        int n = 0;
        for(int i = 0; i< strs.length; i++)
        {
            try {
                Integer.parseInt(strs[i]);
            } catch (NumberFormatException | NullPointerException nfe) {
                continue;
            }
            n++;
        }
        int[] mas = new int[n];
        for(int i = 0, j = 0; i< strs.length; i++)
        {
            try {
                mas[j] = Integer.parseInt(strs[i]);
                j++;
            } catch (NumberFormatException | NullPointerException nfe) {
                continue;
            }
        }
        return mas;
    }

    static String[] Palindrom(String[] strs)
    {
        int n = 0;
        for(int i = 0; i< strs.length; i++)
        {
            try {
                Integer.parseInt(strs[i]);
            } catch (NumberFormatException | NullPointerException nfe) {
                if(strs[i].equals(new StringBuilder(strs[i]).reverse().toString()))
                n++;
            }
        }
        String[] palindrom = new String[n];
        for(int i = 0, j=0; i< strs.length; i++)
        {
            try {
                Integer.parseInt(strs[i]);
            } catch (NumberFormatException | NullPointerException nfe) {
                if(strs[i].equals(new StringBuilder(strs[i]).reverse().toString()))
                {
                    palindrom[j] = strs[i];
                    j++;
                }
            }
        }
        return palindrom;
    }

    static int search(String[] strs, int P)
    {
        int index = -1;
        String sP = Integer.toString(P);
        for(int i = 0; i< strs.length; i++)
        {
            try {
                if(strs[i].equals(sP))
                {
                    index = i;
                    break;
                }
            } catch (NumberFormatException | NullPointerException nfe) {
                continue;
            }
        }
        return index+1;
    }

    static String dltstaples(String str)
    {
        int ind1, ind2;
        ind1 = str.indexOf("(");
        ind2 = str.indexOf(")");
        String strNew = str.substring(0, ind1-1) + str.substring(ind2+1, str.length());
        return strNew;
    }
}
