import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		DecimalFormat dF = new DecimalFormat( "0.0000" );
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
		justification(matr);
		ArrayList<Double> solve = new ArrayList<>(method(matr));
		System.out.println("\nРешение: ");
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

	static void justification(ArrayList<ArrayList<Double>> matr) throws IOException
	{
		DecimalFormat dF = new DecimalFormat( "0.0000" );
		Double norm = 99.0;
		for(int i = 0; i < matr.size(); i++)                  //Число обусловленности
		{
			Double temp = 0.0;
			for (int j = 0; j < matr.get(0).size()-1; j++)
			{
				temp += Math.abs(matr.get(i).get(j));
			}
			if(temp<norm)
				norm = temp;
		}
		if(norm>=1)
		{
			for(int i = 0; i < matr.get(0).size()-1; i++)                  //Число обусловленности
			{
				Double temp = 0.0;
				for (int j = 0; j < matr.size(); j++)
				{
					temp += Math.abs(matr.get(j).get(i));
				}
				if(temp<norm)
					norm = temp;
			}
		}
		if(norm<1)
			System.out.println("Норма матрицы равна " + dF.format(norm) + ". Метод сходится.");
		else
			System.out.println("Норма матрицы равна " + dF.format(norm) + ". Условие не выполнено.");
	}

	static ArrayList<Double> method(ArrayList<ArrayList<Double>> matr)
	{
		ArrayList<Double> solve = new ArrayList<>();
		ArrayList<Double> iter = new ArrayList<>();
		for(int i = 0; i<matr.size(); i++)
		{
			for(int j = matr.get(0).size()-2; j>=0; j--)
			{
				if(j!=i)
					matr.get(i).set(j, -matr.get(i).get(j)/matr.get(i).get(i));
			}
			matr.get(i).set(matr.get(0).size()-1, matr.get(i).get(matr.get(0).size()-1)/matr.get(i).get(i));
			matr.get(i).set(i, 0.0);
		}
		for(int i = 0; i<matr.size(); i++)
		{
			iter.add(0.0);
			solve.add(matr.get(i).get(matr.get(0).size()-1));
		}
		Double previous = 999.0;
		int a = 0;
		Double norm = 0.0;
		while(Math.abs(previous - solve.get(0)) >= 0.00001)
		{
			previous = solve.get(0);
			a++;
			for (int i = 0; i < 5; i++)
			{
				for (int j = 0; j < 5; j++)
				{
					iter.set(i, iter.get(i) + solve.get(j) * matr.get(i).get(j));
				}
				solve.set(i, matr.get(i).get(5) + iter.get(i));
				iter.set(i, 0.0);
			}
			for(int i = 0; i<5; i++)
			{
				norm += solve.get(i);
			}
		}
		System.out.println("\nЧисло итераций: " + a);
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
