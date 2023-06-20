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
		ArrayList<ArrayList<Double>> result = new ArrayList<>(method(matr));
		System.out.println("Собственные векторы: ");
		for(int i = 0; i<5; i++) {
			for(int j = 0; j< 5; j++) {
				System.out.print(dF.format(result.get(j).get(i)) + " ");
			}
			System.out.println();
		}
		System.out.println("\nСпектр: ");
		for(Double i: result.get(5))
			System.out.println(i + " ");
		discrepancy(matr, result);
	}

	static void discrepancy(ArrayList<ArrayList<Double>> A, ArrayList<ArrayList<Double>> result)
	{
		System.out.println("\nНевязки: ");
		ArrayList<Double> r = new ArrayList<>();
		for(int i = 0; i<5; i++)
			r.add(0.0);
		for(int a = 0; a<5; a++) {
			for (int i = 0; i < A.size(); i++) {
				r.set(i, - result.get(5).get(a) * result.get(i).get(a));
				for (int j = 0; j < A.size(); j++) {
					r.set(i, r.get(i) + A.get(i).get(j) * result.get(j).get(a));
				}
				System.out.print(r.get(i) + " ");
			}
			System.out.println();
		}
	}

	static ArrayList<ArrayList<Double>> method(ArrayList<ArrayList<Double>> matr)
	{
		ArrayList<ArrayList<Double>> T = new ArrayList<>();
		ArrayList<ArrayList<Double>> result = new ArrayList<>();
		for(int i = 0; i<5; i++) {
			T.add(new ArrayList<>());
			result.add(new ArrayList<>());
			for (int j = 0; j < 5; j++)
			{
				if(i==j) {
					T.get(i).add(1.0);
					result.get(i).add(1.0);
				}
				else {
					T.get(i).add(0.0);
					result.get(i).add(0.0);
				}
			}
		}
		int a = 0;
		Double norm = 99.0;
		int i1 = 0, j1 = 1;
		Double cos, sin;
		Double m;
		while(Math.abs(norm)>0.00001)
		{
			for(int i = 0; i<5; i++) {
				for (int j = i + 1; j < 5; j++) {
					if(Math.abs(matr.get(i).get(j)) > Math.abs(matr.get(i1).get(j1)))
					{
						i1 = i; j1 = j;
					}
				}
			}
			if(matr.get(i1).get(i1).equals(matr.get(j1).get(j1)))
			{
				cos = 1/Math.sqrt(2); sin = -1/Math.sqrt(2);
			}
			else
			{
				m = 2*matr.get(i1).get(j1)/ (matr.get(i1).get(i1) - matr.get(j1).get(j1));
				cos = Math.sqrt(0.5+0.5/Math.sqrt(1+m*m));
				sin = Math.signum(m)*Math.sqrt(0.5-0.5/Math.sqrt(1+m*m));
			}
			T.get(i1).set(i1, cos);
			T.get(i1).set(j1, -sin);
			T.get(j1).set(i1, sin);
			T.get(j1).set(j1, cos);
			result = multipe(result, T);
			matr = multipe(matr, T);
			matr = multipe(trans(T), matr);
			T.get(i1).set(i1, 1.0);
			T.get(i1).set(j1, 0.0);
			T.get(j1).set(i1, 0.0);
			T.get(j1).set(j1, 1.0);

			norm = 0.0;
			for(int i = 0; i<5; i++) {
				for (int j = 0; j < 5; j++) {
					if(i != j)
						norm += Math.pow(matr.get(i).get(j), 2);
				}
			}
			norm = Math.sqrt(norm);
			++a;
		}
		System.out.println("Число итераций: " + a + "\n");
		ArrayList<Double> solve = new ArrayList<>();
		for(int i = 0; i<5; i++)
			solve.add(matr.get(i).get(i));
		result.add(solve);
		return result;
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
