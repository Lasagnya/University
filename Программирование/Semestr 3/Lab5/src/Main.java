import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        String line = "";
        while (br.ready()) {
            line += br.readLine() + " ";
        }
        System.out.println(line);
        ArrayList<String> IPs = new ArrayList<String>();
        Pattern pat = Pattern.compile("\\b(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)[.](25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)[.](25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)[.](25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\b"); //"\\b([\\d]{1,3}[[.]]){3}[\\d]{1,3}\\b"    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)[.](25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)[.](25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)[.](25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)"
        Matcher matcher = pat.matcher(line);
        while(matcher.find()) {
            IPs.add(line.substring(matcher.start(),matcher.end()));
        }
        for(String i:IPs){
            System.out.print(i+ " ");
        }
        System.out.println("\n");
        br = new BufferedReader(new FileReader("input2.txt"));
        line = "";
        while (br.ready()) {
            line += br.readLine() + " ";
        }
        System.out.println(line);
        Search(line);
        br = new BufferedReader(new FileReader("input3.txt"));
        line = "";
        while (br.ready()) {
            line += br.readLine() + " ";
        }
        System.out.println('\n');
        System.out.println(line);
        line = change(line);
        System.out.print("New line: " + line);
    }


    static void Search(String str)
    {
        ArrayList<String> tegs = new ArrayList<String>();
        ArrayList<String> text = new ArrayList<String>();
        Pattern pat1 = Pattern.compile("<.+?>");
        Matcher matcher1 = pat1.matcher(str);
        Pattern pat2 = Pattern.compile(">([^ <].+?)<"); //">[^< ].+?<"
        Matcher matcher2 = pat2.matcher(str);
        while(matcher1.find()) {
            tegs.add(str.substring(matcher1.start(),matcher1.end()));
        }
        System.out.print("Tegs: ");
        for(String i:tegs){
            System.out.print(i+ " ");
        }
        System.out.println();
        System.out.print("Text: ");
        while(matcher2.find()) {
            text.add(matcher2.group(1));
        }
        for(String i:text){
            System.out.print(i+ " ");
        }

    }

    static String change(String str)
    {
        Pattern pat = Pattern.compile("\\b([a-z&&[^ ]])([a-z&&[^ ]]*)\\b");
        Matcher mat = pat.matcher(str);
        StringBuffer strbuf = new StringBuffer();
        while(mat.find())
        {
            mat.appendReplacement(strbuf, mat.group(1).toUpperCase()+mat.group(2));
        }
        mat.appendTail(strbuf);
        return strbuf.toString();
    }
}
