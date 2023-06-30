import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		StreamTokenizer input = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
		input.nextToken();
		int n = (int) input.nval;	//На ввод подаётся сначала кол-во предметов, потом их стоимость, потом их веса, потом максимальный вес.
		int[] cost = new int[n];	//Могут разделяться пробелами и новой строкой. В принципе, без разницы.
		for (int i = 0; i < n; i++) {
			input.nextToken();
			cost[i] = (int) input.nval;
		}
		int[] weight = new int[n];
		for (int i = 0; i < n; i++) {
			input.nextToken();
			weight[i] = (int) input.nval;
		}
		input.nextToken();
		int maxWeight = (int) input.nval;
		int[] maximum = new int[maxWeight+1];
		ArrayList<ArrayList<Integer>> items = new ArrayList<>();	//В вектор заносятся добавляемые предметы.
		for (int w = 0; w <= maxWeight; w++) {
			items.add(new ArrayList<>());
		}
		for (int w = 1; w <= maxWeight; w++) {
			for (int i = 0; i < n; i++) {
				if (weight[i] > w)
					continue;
				int maxCost_per_weight = cost[i] + maximum[w - weight[i]];
				if (maxCost_per_weight > maximum[w]) {
					maximum[w] = maxCost_per_weight;
					items.get(w).clear();								//Удаляем старый список.
					items.get(w).addAll(items.get(w-weight[i]));		//Переносим список с веса без текущего предмета.
					items.get(w).add(i);								//Добавляем текущий предмет.
				}
			}
		}
		System.out.println("Максимальная цена - " + maximum[maxWeight]);
		System.out.println("Предметы: " + items.get(maxWeight));
	}
}