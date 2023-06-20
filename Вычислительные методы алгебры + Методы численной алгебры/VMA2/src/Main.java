import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<ArrayList<Double>> matr = new ArrayList<>();
        input(matr);
        ArrayList<ArrayList<Double>> matr2 = new ArrayList<>();
        for(int i = 0; i<matr.size(); i++)
        {
            ArrayList<Double> intList = new ArrayList<>();
            for (int j = 0; j < matr.get(0).size(); j++)
            {
                intList.add(matr.get(i).get(j));
            }
            matr2.add(intList);
        }
        matr = method(matr);
        ArrayList<Double> solve = new ArrayList<>(methodrvr(matr));
        System.out.println("Решение:");
        DecimalFormat dF = new DecimalFormat( "0.0000" );
        for(int i = 0; i<solve.size(); i++) {
            System.out.print(dF.format(solve.get(i)) + " ");
        }
        System.out.println('\n');
        ArrayList<Double> result = new ArrayList<>();
        System.out.println("Невязка:");
        System.out.print((result = discrepancy(matr2, solve)).toString());
    }

    static ArrayList<ArrayList<Double>> input (ArrayList<ArrayList<Double>> matr) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        ArrayList<String> str;
        String line;
        while((line = br.readLine()) != null)
        {
            ArrayList<Double> intList = new ArrayList<>();
            str = new ArrayList<>(Arrays.asList(line.split(" ")));
            for(int j = 0; j<str.size(); j++)
            {
                intList.add(Double.parseDouble(str.get(j)));
            }
            matr.add(intList);
        }
        System.out.println("Матрица:");
        output(matr);
        return matr;
    }

    static void output(ArrayList<ArrayList<Double>> matr)
    {
        DecimalFormat dF = new DecimalFormat( "0.0000" );
        for(int i = 0; i<matr.size(); i++) {
            for (int j = 0; j<matr.get(0).size(); j++)
                System.out.print(dF.format(matr.get(i).get(j)) + " ");
            System.out.println();
        }
        System.out.println();
    }

    static ArrayList<ArrayList<Double>> method(ArrayList<ArrayList<Double>> matr)
    {
        matr.get(0).set(1, matr.get(0).get(1)/-1.0*matr.get(0).get(0));
        matr.get(0).set(5, matr.get(0).get(5)/matr.get(0).get(0));
        matr.get(0).set(0, 1.0);
        for(int i = 1; i<4; i++)
        {
            matr.get(i).set(5, (matr.get(i).get(i-1)*matr.get(i-1).get(5)-matr.get(i).get(5))/(matr.get(i).get(i)*(-1)-matr.get(i).get(i-1)*matr.get(i-1).get(i)));
            matr.get(i).set(i+1, matr.get(i).get(i+1)/((-1)*matr.get(i).get(i)-matr.get(i).get(i-1)*matr.get(i-1).get(i)));
            matr.get(i).set(i, 1.0);
            matr.get(i).set(i-1, 0.0);
        }
        matr.get(4).set(5, (matr.get(4).get(4-1)*matr.get(4-1).get(5)-matr.get(4).get(5))/(-1*matr.get(4).get(4)-matr.get(4).get(4-1)*matr.get(4-1).get(4)));
        matr.get(4).set(4, 1.0);
        matr.get(4).set(3, 0.0);
     //   System.out.println("Матрица:");
     //   output(matr);
        return matr;
    }

      static ArrayList<Double> methodrvr(ArrayList<ArrayList<Double>> matr)
    {
        ArrayList<Double> solve = new ArrayList<>();
        solve.add(matr.get(4).get(5));
        for(int i = 3; i>=0; i--)
        {
            solve.add(matr.get(i).get(5) + matr.get(i).get(i+1)*matr.get(i+1).get(5));
        }
        Collections.reverse(solve);
        return solve;
    }

    static ArrayList<Double> discrepancy(ArrayList<ArrayList<Double>> matr, ArrayList<Double> solve)
    {
        ArrayList<Double> result = new ArrayList<>(5);
        for (int i = 0; i < matr.size(); i++)
        {
            result.add(-matr.get(i).get(5));
            for (int j = 0; j < solve.size(); j++)
            {
                result.set(i, result.get(i)+matr.get(i).get(j)*solve.get(j));
            }
        }
        return result;
    }
}
