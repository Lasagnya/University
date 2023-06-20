import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		st.nextToken();
		int m = (int) st.nval;
		st.nextToken();
		int c = (int) st.nval;
		st.nextToken();
		int n = (int) st.nval;
		int[] mass = new int[m];
		for(int i = 0; i < m; i++)
			mass[i] = -1;
		for(int i = 0; i < n; i++) {
			st.nextToken();
			int x = (int) st.nval;
			for(int j = 0; j < m; j++) {
				int k = ((x % m) + c * j) % m;
				if(mass[k] == x)
					break;
				if(mass[k] == -1) {
					mass[k] = x;
					break;
				}
			}
		}
		for(int i = 0; i < m; i++)
			bw.write(mass[i] + " ");
		bw.close();
	}
}
