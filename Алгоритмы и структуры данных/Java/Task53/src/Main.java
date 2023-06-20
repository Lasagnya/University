import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		st.nextToken();
		int n = (int) st.nval;
		st.nextToken();
		int m = (int) st.nval;
		int[][] matr = new int[n][m];
		int[][] way = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				st.nextToken();
				matr[i][j] = (int) st.nval;
				way[i][j] = Integer.MAX_VALUE;
			}
		if (m > n) {
			bw.write("-1");
			bw.close();
			return;
		}
		if (m == n) {
			int result = 0;
			for (int i = 0; i < n; i++)
				result += matr[i][i];
			bw.write(String.valueOf(result));
			bw.close();
			return;
		}
		way[0][0] = matr[0][0];
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < m; j++) {
				if (way[i][j] != Integer.MAX_VALUE) {
					for (int t = j - 1; t <= j + 1; t++) {
						if (t >= 0 && t < m) {
							int sum = way[i][j] + matr[i + 1][t];
							if (way[i + 1][t] > sum)
								way[i + 1][t] = sum;
						}
					}
				}
			}
		}
		bw.write(String.valueOf(way[n-1][m-1]));
		bw.close();
	}
}
