import java.io.*;

public class Main {
	static int n = 0;
	static int[][] matr;
	static int[] marks;
	static boolean result = true;

	public static void main(String[] args) throws IOException {
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.in")));
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.out"));
		st.nextToken();
		n = (int) st.nval;
		matr = new int[n][n];
		for(int i = 0; i < n; i++)
			for(int j = 0; j < n; j++) {
				st.nextToken();
				matr[i][j] = (int) st.nval;
			}
		marks = new int[n];
		int mark = 2;
		for(int i = 0; i < n; i++) {
			mark = invert(mark);
			if(!result)
				break;
			if(marks[i] == 0)
				bfs(i, mark);
		}
		if(result) {
			bw.write("YES\n");
			for(int i = 0; i < n; i++)
				if(marks[i] == 1)
					bw.write((i+1) + " ");
		}
		else
			bw.write("NO");
		bw.close();
	}

	static void bfs(int vertex, int mark) {
		if(!result)
			return;
		marks[vertex] = mark;
		for(int i = 0; i < n; i++) {
			if(matr[vertex][i] == 1) {
				if(marks[i] == 0)
					bfs(i, invert(mark));
				else if(marks[i] == mark)
					result = false;
			}
		}
	}

	static int invert(int x) {
		return x == 1 ? 2 : 1;
	}
}
