import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
//        Formatter fmt = new Formatter();
//        fmt.format("PI = %f%n", Math.PI);
        DecimalFormat dF = new DecimalFormat( "0.0000" );
        BufferedReader br = new BufferedReader(new FileReader("input.txt"));
        Double[][] matr = new Double[6][5];
        String[] str = new String[6];
        for(int i = 0; i< matr[i].length; i++) {
            str = br.readLine().split(" ");
            for(int j = 0; j<matr.length; j++)
            {
                matr[j][i] = Double.parseDouble(str[j]);
            }
        }
        Double[][] matr2 = new Double[6][5];
        for(int a = 0; a< matr.length; a++)
        {
            for (int b = 0; b < matr[0].length; b++)
            {
                matr2[a][b] = matr[a][b];
            }
        }
        for(int i = 0; i< matr[i].length; i++)
        {
            for (int j = 0; j < matr.length; j++)
            {
                System.out.print(dF.format(matr[j][i]) + "  ");
            }
            System.out.println();
        }
        System.out.println();
        Double main = 0.0;
        for(int i = 0; i<matr[i].length; i++)
        {
            int index = search(matr, i);
            Double[] copy = matr[i];
            matr[i] = matr[index];
            matr[index] = copy;
            main = matr[i][i];
            for (int j = i; j < matr.length; j++)
            {
                matr[j][i] /= main;
            }

            for(int j = i+1; j<matr[0].length; j++)
            {
                Double division = matr[i][j];
                for(int k = 0; k<matr.length; k++)
                {
                    matr[k][j] -= (matr[k][i]*division);
                }
            }
        }
        output(matr);

        Double[] solve = new Double[5];
        for(int i = matr.length-2; i>= 0; i--)
        {
            for(int j = matr.length-2; j>i; j--)
            {
                matr[5][i] -= matr[5][j]*matr[j][i];
            }
            solve[4-i] = matr[5][i];
        }
        Double copy = 0.0;
        for(int i = 0; i<solve.length/2; i++)
        {
            copy = solve[i];
            solve[i] = solve[solve.length-i-1];
            solve[solve.length-i-1] = copy;
        }
        for(int i = 0; i< solve.length; i++)
        {
            System.out.print(dF.format(solve[i]) + " ");
        }
        Double[] result = new Double[5];
        for(int i = 0; i< result.length; i++)
        {
            result[i] = 0.0;
        }
        for(int i = 0; i<matr2[i].length; i++)
        {
            for (int j = 0; j < matr2.length-1; j++)
            {
                result[i] += matr2[j][i]*solve[j];
            }
        }
        System.out.println();
        for(int i = 0; i< result.length; i++)
        {
            System.out.print(dF.format(result[i]) + " ");
        }
    }

    static int search(Double[][] matr, int line)
    {
        Double biger = matr[0][line];
        int index = 0;
        for(int i = 0; i< matr[0].length; i++)
        {
            if(Math.abs(biger) < Math.abs(matr[i][line]))
            {
                biger = matr[i][line];
                index = i;
            }
        }
        return index;
    }

    static void output(Double[][] matr)
    {
        DecimalFormat dF = new DecimalFormat( "0.0000" );
        for(int a = 0; a< matr[a].length; a++)
        {
            for (int b = 0; b < matr.length; b++)
            {
                System.out.print(dF.format(matr[b][a]) + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }
}