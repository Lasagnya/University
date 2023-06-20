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
		discrepancy(matr2, method(matr));
	}

	static void discrepancy(ArrayList<ArrayList<Double>> A, ArrayList<ArrayList<Double>> result)
	{
		System.out.println("\nНевязки: ");
		ArrayList<Double> r = new ArrayList<>();
		for(int i = 0; i<5; i++)
			r.add(0.0);
		for(int a = 0; a<5; a++) {
			for (int i = 0; i < A.size(); i++) {
				r.set(i, - result.get(5).get(a) * result.get(a).get(i));
				for (int j = 0; j < A.size(); j++) {
					r.set(i, r.get(i) + A.get(i).get(j) * result.get(a).get(j));
				}
				System.out.print(r.get(i) + " ");
			}
			System.out.println();
		}
	}

	static ArrayList<ArrayList<Double>> method(ArrayList<ArrayList<Double>> matr)
	{
		ArrayList<ArrayList<Double>> M = new ArrayList<>();
		ArrayList<ArrayList<Double>> M1 = new ArrayList<>();
		ArrayList<ArrayList<Double>> Mij = new ArrayList<>();
		for(int i = 0; i<5; i++) {
			M.add(new ArrayList<>());
			M1.add(new ArrayList<>());
			Mij.add(new ArrayList<>());
			for (int j = 0; j < 5; j++)
			{
				if(i==j) {
					M.get(i).add(1.0);
					M1.get(i).add(1.0);
					Mij.get(i).add(1.0);
				}
				else {
					M.get(i).add(0.0);
					M1.get(i).add(0.0);
					Mij.get(i).add(0.0);
				}
			}
		}
		for(int a = 0; a<4; a++){
			for(int i = 0; i<5; i++){
				M.get(3-a).set(i, -matr.get(4-a).get(i)/matr.get(4-a).get(3-a));
				M1.get(3-a).set(i, matr.get(4-a).get(i));
				if(i==(3-a)){
					M.get(3-a).set(i, 1/matr.get(4-a).get(3-a));
				}
			}
			matr = multipe(M1, matr);
			matr = multipe(matr, M);
			Mij = multipe(Mij, M);
			for(int i = 0; i<5; i++){
				M.get(3-a).set(i, 0.0);
				M1.get(3-a).set(i, 0.0);
				if(i==(3-a)){
					M.get(3-a).set(i, 1.0);
					M1.get(3-a).set(i, 1.0);
				}
			}
		}

		ArrayList<Double> sz = new ArrayList<>();
		sz.add(0.324); sz.add(0.435); sz.add(0.970); sz.add(1.316); sz.add(1.656);
		ArrayList<ArrayList<Double>> szs = new ArrayList<>();
		for(int i = 0; i<5; i++) {
			szs.add(new ArrayList<>());
			for (int j = 0; j<5; j++){
				szs.get(i).add(Math.pow(sz.get(i), 4-j));
			}
		}
		DecimalFormat dF = new DecimalFormat( "0.0000" );
		System.out.println("Система собственных векторов:");
		ArrayList<ArrayList<Double>> sv = new ArrayList<>();
		for(int a = 0; a<5; a++) {
			sv.add(new ArrayList<>(Arrays.asList(0.0, 0.0, 0.0, 0.0, 0.0)));
			for (int i = 0; i < Mij.size(); i++) {
				for (int j = 0; j < Mij.size(); j++) {
					sv.get(a).set(i, sv.get(a).get(i) + Mij.get(i).get(j) * szs.get(a).get(j));
				}
				System.out.print(dF.format(sv.get(a).get(i)) + " ");
			}
			System.out.println();
		}
		sv.add(sz);
		return sv;
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
