import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("in.txt")));
		st.nextToken();
		int x = (int) st.nval;
		st.nextToken();
		int y = (int) st.nval;
		st.nextToken();
		int z = (int) st.nval;
		st.nextToken();
		String A = st.sval;
		st.nextToken();
		String B = st.sval;
		int[][] matr = new int[A.length()+1][B.length()+1];
		matr[0][0] = 0;
		for(int i = 1; i < matr.length; i++)
			matr[i][0] = matr[i-1][0] + x;
		for(int i = 1; i < matr[0].length; i++)
			matr[0][i] = matr[0][i-1] + y;
		for(int i = 1; i < matr.length; i++) {
			for(int j = 1; j < matr[0].length; j++) {
				if(A.charAt(i-1) == B.charAt(j-1))
					matr[i][j] = matr[i-1][j-1];
				else {
					int a = matr[i - 1][j] + x;
					int b = matr[i][j - 1] + y;
					int c = matr[i - 1][j - 1] + z;
					matr[i][j] = (a < b) ? ((a < c) ? a : c) : ((b < c) ? b : c);
				}
			}
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));
		bw.write(String.valueOf(matr[matr.length-1][matr[0].length-1]));
		bw.close();
	}
}
