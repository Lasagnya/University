import java.io.*;
import java.util.Stack;

public class Main {
	public static void main(String[] args) throws IOException {
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("in.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt"));
		st.nextToken();
		int n = (int) st.nval;
		st.nextToken();
		int m = (int) st.nval;
		int[][] matr = new int[n][n];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++) {
				st.nextToken();
				matr[i][j] = (int) st.nval;
			}
		Stack<Integer> tube = new Stack<>();
		Stack<Integer> tube2 = new Stack<>();
		st.nextToken();
		tube.push((int) st.nval);
		for(int i = 0; i < m - 1; i++) {
			st.nextToken();
			tube2.push((int) st.nval);
			do {
				int oldElem = tube.pop();
				int newElem = tube2.pop();
				if (matr[newElem-1][oldElem-1] != 0)
					tube2.push(matr[newElem-1][oldElem-1]);
				else {
					tube.push(oldElem);
					tube.push(newElem);
					while(!tube2.empty())
						tube.push(tube2.pop());
					break;
				}
			} while(!tube.empty());
			while(!tube2.empty())
				tube.push(tube2.pop());
		}
		bw.write(String.valueOf(tube.pop()));
		while(!tube.empty())
			bw.write(" " + tube.pop());
		bw.close();
	}
}
