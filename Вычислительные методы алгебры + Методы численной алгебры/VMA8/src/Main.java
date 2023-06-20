import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<ArrayList<Double>> matr = new ArrayList<>(input());
        method(matr);
    }

    static void discrepancy(ArrayList<ArrayList<Double>> A, ArrayList<Double> sv, Double sz)
    {
        System.out.println("\nНевязки: ");
        ArrayList<Double> r = new ArrayList<>();
        for(int i = 0; i<5; i++)
            r.add(0.0);
        for (int i = 0; i < A.size(); i++) {
            r.set(i, - sz * sv.get(i));
            for (int j = 0; j < A.size(); j++) {
                r.set(i, r.get(i) + A.get(i).get(j) * sv.get(j));
            }
            System.out.print(r.get(i) + " ");
        }
        System.out.println();
    }

    static void method(ArrayList<ArrayList<Double>> matr)
    {
        DecimalFormat dF = new DecimalFormat( "0.0000" );
        int a = 0;
        ArrayList<Double> y1 = new ArrayList<>(Arrays.asList(1.0, 0.0, 0.0, 0.0, 0.0));
        ArrayList<Double> y2 = new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0));
        Double l1 = 1.0, l2 = 0.0, mn1, mn2, norm;
        while(Math.abs(l1-l2)>0.00001) {
            ++a;
            for (int i = 0; i < 5; i++) {
                y2.set(i, 0.0);
                for (int j = 0; j < 5; j++) {
                    y2.set(i, y2.get(i) + matr.get(i).get(j) * y1.get(j));
                }
            }

            mn1 = 0.0; mn2 = 0.0;
            norm = norm(y2);
            for (int i = 0; i < 5; i++) {
                mn1 += y2.get(i) * y1.get(i);
                mn2 += y1.get(i) * y1.get(i);
                y2.set(i, y2.get(i) / norm);
            }
            l1 = mn1 / mn2;
            for (int i = 0; i < 5; i++) {
                y1.set(i, 0.0);
                for (int j = 0; j < 5; j++) {
                    y1.set(i, y1.get(i) + matr.get(i).get(j) * y2.get(j));
                }
            }

            mn1 = 0.0;
            mn2 = 0.0;
            norm = norm(y1);
            for (int i = 0; i < 5; i++) {
                mn1 += y1.get(i) * y2.get(i);
                mn2 += y2.get(i) * y2.get(i);
                y1.set(i, y1.get(i) / norm);
            }
            l2 = mn1 / mn2;
        }
        System.out.println("Количество итераций: " + a);
        System.out.println("Максимальное собственное значение:");
        System.out.println(dF.format(l1) + "\n");
        System.out.println("Соответствующий собственный вектор:");
        for(Double i: y1)
            System.out.println(dF.format(i));
        discrepancy(matr, y1, l2);
    }

    static Double norm(ArrayList<Double> vector){
        Double norm = 0.0;
        for(Double i: vector){
            norm += i*i;
        }
        return Math.sqrt(norm);
    }

    static ArrayList<ArrayList<Double>> input() throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        ArrayList<ArrayList<Double>> matr = new ArrayList<>();
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
        ArrayList<ArrayList<Double>> matr2 = new ArrayList<>(trans(matr));
        matr = new ArrayList<>(multipe(matr2, matr));
        System.out.println("Матрица:");
        output(matr);
        return matr;
    }

    static ArrayList<ArrayList<Double>> multipe(ArrayList<ArrayList<Double>> matr2, ArrayList<ArrayList<Double>> matr)
    {
        ArrayList<ArrayList<Double>> resmatr = new ArrayList<>();
        for (int i = 0; i < matr.size(); i++)
        {
            ArrayList<Double> list = new ArrayList<>();
            for (int j = 0; j < matr.size(); j++)
            {
                list.add(0.0);
                for (int k = 0; k < matr.size(); k++)
                {
                    list.set(j, list.get(j) + matr2.get(i).get(k) * matr.get(k).get(j));
                }
            }
            resmatr.add(list);
        }
        return resmatr;
    }

    static ArrayList<ArrayList<Double>> trans(ArrayList<ArrayList<Double>> matr)
    {
        ArrayList<ArrayList<Double>> matr2 = new ArrayList<>();
        for(int i = 0; i<matr.size(); i++)
        {
            ArrayList<Double> intList = new ArrayList<>();
            for (int j = 0; j < matr.get(0).size(); j++)
            {
                intList.add(matr.get(j).get(i));
            }
            matr2.add(intList);
        }
        return matr2;
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
}
