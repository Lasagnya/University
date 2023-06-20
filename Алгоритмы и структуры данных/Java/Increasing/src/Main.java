import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
		st.nextToken();
		int n = (int) st.nval;
		int length = 0;
		int[] posl = new int[n];
		int[] minValues = new int[n];
		for(int i = 0; i < n; i++) {
			st.nextToken();
			posl[i] = (int) st.nval;
			minValues[i] = Integer.MAX_VALUE;
		}
		minValues[0] = posl[0];
		for(int i = 1; i < posl.length; i++) {
			int index = binSearch(minValues, i, posl[i]);
			minValues[index] = posl[i];
			if(length < index)
				length = index;
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		bw.write(String.valueOf(length + 1));
		bw.close();
	}

	static int binSearch(int[] minValues, int right, int key) {
		int m = 0;
		int l = 0, r = right;
		while(l <= r) {
			m = (l + r) / 2;
			if(key < minValues[m])
				r = m - 1;
			if(minValues[m] == key)
				return m;
			if(m + 1 <= r && key <= minValues[m+1]) {
				return m + 1;
			}
			if(key > minValues[m])
				l = m + 1;
		}

		if(m == l)
			return m;
		else
			return m + 1;
	}
}
