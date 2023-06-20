import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		int n;
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
		st.nextToken();
		n = (int) st.nval;
		int[][] sizes = new int[n][2];
		for(int i = 0; i<n; i++) {
			st.nextToken();
			sizes[i][0] = (int) st.nval;
			st.nextToken();
			sizes[i][1] = (int) st.nval;
		}
		int[][] matr = new int[n][n];
		for(int a = 1; a < n; a++) {
			for (int i = 0; i < n - a; i++) {
				matr[i+a][i] = Integer.MAX_VALUE;
				for (int j = i; j < i + a; j++) {
					int temp = matr[j][i] + matr[i + a][j + 1] + sizes[i][0] * sizes[j + 1][0] * sizes[i+a][1];
					if(temp < matr[i+a][i])
						matr[i+a][i] = temp;
				}
			}
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		bw.write(String.valueOf(matr[n-1][0]));
		bw.close();
	}
}
