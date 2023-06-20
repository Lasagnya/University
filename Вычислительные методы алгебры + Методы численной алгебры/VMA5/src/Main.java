import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		DecimalFormat dF = new DecimalFormat( "0.0000" );
		ArrayList<ArrayList<Double>> matr = new ArrayList<>(input());
		ArrayList<Double> solve = new ArrayList<>(method(matr));
		System.out.println("Коэффициенты собественного многочлена: ");
		for(Double i: solve) {
			System.out.print(dF.format(i) + " ");
		}
		discrepancy(solve);
	}

	static void discrepancy(ArrayList<Double> solve)
	{
		System.out.println("\nНевязка: ");
		double x = 1.6559320508388304;
		System.out.println(-Math.pow(x, 5) + Math.pow(x, 4)*solve.get(0) + solve.get(1)*Math.pow(x, 3) + solve.get(2)*Math.pow(x, 2) + solve.get(3)*Math.pow(x, 1) + solve.get(4));
	}

	static ArrayList<Double> method(ArrayList<ArrayList<Double>> matr)
	{
		ArrayList<ArrayList<Double>> vector = new ArrayList<>();
		vector.add(new ArrayList<>());
		vector.get(0).add(1.0);
		for(int i = 0; i<4; i++) {
			vector.get(0).add(0.0);
		}
		for(int a = 1; a<6; a++) {
			vector.add(new ArrayList<>());
			for (int i = 0; i < matr.size(); i++) {
				vector.get(a).add(0.0);
				for (int j = 0; j < matr.size(); j++) {
					vector.get(a).set(i, vector.get(a).get(i) + matr.get(i).get(j) * vector.get(a-1).get(j));
				}
			}
		}
		return gaussMethod(vector);
	}

	static ArrayList<Double> gaussMethod(ArrayList<ArrayList<Double>> vectors)
	{
		for(int i = 0; i<vectors.get(0).size(); i++)
		{
			Double main = vectors.get(i).get(i);
			for (int j = i; j < vectors.size(); j++)
			{
				vectors.get(j).set(i, vectors.get(j).get(i)/main);
			}
			for(int j = i+1; j<vectors.get(0).size(); j++)
			{
				Double division = vectors.get(i).get(j);
				for(int k = 0; k< vectors.size(); k++)
				{
					vectors.get(k).set(j, vectors.get(k).get(j) - vectors.get(k).get(i)*division);
				}
			}
		}

		ArrayList<Double> solve = new ArrayList<>();
		for(int i = vectors.size()-2; i>= 0; i--)
		{
			for(int j = vectors.size()-2; j>i; j--)
			{
				vectors.get(5).set(i, vectors.get(5).get(i) - vectors.get(5).get(j)*vectors.get(j).get(i));
			}
			solve.add(vectors.get(5).get(i));
		}
		return solve;
	}

	static ArrayList<ArrayList<Double>> input() throws IOException
	{
		ArrayList<ArrayList<Double>> matr = new ArrayList<>();
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
		matr = new ArrayList<>(multipe(matr));
		System.out.println("Матрица:");
		output(matr);
		return matr;
	}

	static ArrayList<ArrayList<Double>> multipe(ArrayList<ArrayList<Double>> matr)
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
		ArrayList<ArrayList<Double>> resmatr = new ArrayList<>();
		for (int i = 0; i < matr.size(); i++)             //Умножение матриц
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
