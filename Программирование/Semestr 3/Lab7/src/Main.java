import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<Integer, String> map = new TreeMap<>();
        ArrayList<ArrayList<Integer>> matr = new ArrayList<ArrayList<Integer>>();
        inputMatr(matr, "input.txt");
        search(matr);
    }

    static ArrayList<ArrayList<Integer>> inputMatr(ArrayList<ArrayList<Integer>> matr, String file) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<String> str;
        String line;
        while((line = br.readLine()) != null)
        {
            ArrayList<Integer> intList = new ArrayList<>();
            str = new ArrayList<>(Arrays.asList(line.split(" ")));
            for(int j = 0; j<str.size(); j++)
            {
                intList.add(Integer.parseInt(str.get(j)));
            }
            matr.add(intList);
        }
        System.out.println("Матрица:");
        output(matr);
        return matr;
    }

    static void output(ArrayList<ArrayList<Integer>> matr)
    {
        for(int i = 0; i<matr.size(); i++) {
            for (int j = 0; j<matr.get(0).size(); j++)
                System.out.print(matr.get(i).get(j) + " ");
            System.out.println();
        }
    }

    static void search(ArrayList<ArrayList<Integer>> matr) throws IOException
    {
        Map<Integer, Integer> map = new TreeMap<>();
        int[][] dp = new int[matr.size()][matr.get(0).size()];
        for(int i = 0; i<dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++)
                dp[i][j] = 0;
        }
        int a = 1;
        int row1 = 0, col1 = 0, row2 = 0, col2 = 0;
        for(int i = 0; i<matr.size(); i++) {
            for (int j = 0; j<matr.get(0).size(); j++)
            {
            //    System.out.print(matr.get(i).get(j) + " ");
                if(dp[i][j]==0)
                {
                    row1 = i;
                    col1 = j;
                    row2 = i;
                    col2 = j;
                    while ((col1 < matr.get(0).size()-1) && matr.get(row1).get(col1).equals(matr.get(row1).get(col1 + 1))) {
                        ++col1;
                    }
                    col2 = col1;
                    col1 = j;
                    while ((row1<matr.size()-1) && matr.get(row1).get(col1).equals(matr.get(row1 + 1).get(col1))) {
                        ++row1;
                        while ((col1< matr.get(0).size()-1) && matr.get(row1).get(col1).equals(matr.get(row1).get(col1 + 1))) {
                            ++col1;
                        }
                        if (col1 < col2)
                            col2 = col1;
                        col1 = j;
                    }
                    if((row1!=i) || (col2!=j))
                    {
                        map.put(i*matr.get(0).size()+j, row1*matr.get(0).size()+col2);
                        for (int b = i; b <= row1; b++) {
                            for (int c = j; c <= col2; c++) {
                                dp[b][c] = a;
                            }
                        }
                        ++a;
                    }
                }
            }
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"))) {
            Iterator<Map.Entry<Integer, Integer>> itr = map.entrySet().iterator();
            System.out.println("\nПодматрицы:");
            while (itr.hasNext()) {
                Map.Entry<Integer, Integer> entry = itr.next();
                for (int i = entry.getKey() / matr.get(0).size(); i <= entry.getValue() / matr.get(0).size(); i++) {
                    for (int j = entry.getKey() % matr.get(0).size(); j <= entry.getValue() % matr.get(0).size(); j++) {
                        System.out.print(matr.get(i).get(j) + " ");
                        bw.write(matr.get(i).get(j) + " ");
                    }
                    System.out.println();
                    bw.write("\n");
                }
            }
        }
    }
}
