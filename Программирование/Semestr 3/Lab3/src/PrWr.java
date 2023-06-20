import java.io.*;
import java.math.*;
import java.text.DecimalFormat;
import java.util.*;

class Search
{
    static int srch(Double[] mas)
    {
        int a = 0;
        for(int i=0; i< mas.length; i++){
            if(mas[i]>mas[a]){
                a=i;
            }
        }
        return a;
    }
    static int binsrch(Double[] mas, Double a)
    {
        int firstIndex = 0;
        int lastIndex = mas.length - 1;

        // условие прекращения (элемент не представлен)
        while(firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            // если средний элемент - целевой элемент, вернуть его индекс
            if (mas[middleIndex] == a) {
                return middleIndex;
            }

            // если средний элемент меньше
            // направляем наш индекс в middle+1, убирая первую часть из рассмотрения
            else if (mas[middleIndex] < a)
                firstIndex = middleIndex + 1;

                // если средний элемент больше
                // направляем наш индекс в middle-1, убирая вторую часть из рассмотрения
            else if (mas[middleIndex] > a)
                lastIndex = middleIndex - 1;

        }
        return -1;
    }
}

class Biger implements Comparator<Double> {

    @Override
    public int compare(Double o1, Double o2) {
        if(o1>o2)
        return 1;
        if(o1<o2)
            return -1;
        return 0;
    }
}

public class PrWr {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DecimalFormat dF = new DecimalFormat( "00.000" );
        System.out.println("Input n");
        int n = 5;//scan.nextInt();
        Double[][] matr = new Double[n][n];
        for(int i=0; i< matr.length; i++){
            for(int j=0; j<matr[0].length; j++){
                matr[i][j]=Math.random()*100;
            }
        }
        for(int i=0; i< matr.length; i++){
            for(int j=0; j<matr[0].length; j++){
                System.out.print(dF.format(matr[i][j])+"   ");
            }
            System.out.println();
        }
        System.out.println();
        dF = new DecimalFormat( "0000.000" );
        Double[][] matr2 = new Double[n][n];
        for(int i=0; i< matr2.length; i++){
            int bigest = Search.srch(matr[i]);
            for(int j=0; j<matr2[0].length; j++){
                matr2[i][j]=matr[i][j]*matr[i][bigest];
            }
        }
        for(int i=0; i< matr2.length; i++){
            for(int j=0; j<matr2[0].length; j++){
                System.out.print(dF.format(matr2[i][j])+"   ");
            }
            System.out.println();
        }
        System.out.println();
        Comparator comp = new Biger();
        Arrays.sort(matr2[matr2.length-1],comp);
        for(int i=0; i< matr2.length; i++){
            System.out.print(dF.format(matr2[matr2.length-1][i])+"   ");
        }
        System.out.println();
        int index = Search.binsrch(matr2[matr2.length-1], matr2[matr2.length-1][2]);
        System.out.print(index);
    }
}
