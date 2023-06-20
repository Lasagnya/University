import java.io.*;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		st.nextToken();
		int n = (int) st.nval;
		st.nextToken();
		int m = (int) st.nval;
		st.nextToken();
		int q = (int) st.nval;
		int[] parent = new int[n+1];
		int[] size = new int[n+1];
		int roads[][] = new int[m][2];
		int quakes[] = new int[q];
		int disjoint = n;
		boolean res[] = new boolean[q];
		for(int i = 1; i < n + 1; i++) {
			parent[i] = i;
			size[i] = 1;
		}
		for(int i = 0; i < m; i++) {
			st.nextToken();
			roads[i][0] = (int) st.nval;
			st.nextToken();
			roads[i][1] = (int) st.nval;
		}
		for(int i = 0; i < q; i++) {
			st.nextToken();
			quakes[i] = (int) st.nval;
		}
		if(q < m) {
			int[] roadsAfter = new int[m-q];
			int[]quakesSort = new int[q];
			for(int i = 0; i < q; i++)
				quakesSort[i] = quakes[i];
			Arrays.sort(quakesSort);
			for(int i = 1, j = 0, k = 0; i <= m; i++) {
				if(quakesSort[j] != i) {
					roadsAfter[k] = i;
					k++;
				} else if(j < q - 1)
					j++;
			}
			for(int i = 0; i < m - q; i++) {
				int a = roads[roadsAfter[i]-1][0];
				int b = roads[roadsAfter[i]-1][1];
				int a_root = a, b_root = b;
				while(a_root != parent[a_root])
					a_root = parent[a_root];
				while(b_root != parent[b_root])
					b_root = parent[b_root];
				if(a_root != b_root) {
					if(size[a_root] >= size[b_root]) {
						while(b != parent[b]) {
							int b1 = b;
							b = parent[b];
							parent[b1] = a_root;
						}
						parent[b] = a_root;
						size[a_root] += size[b_root];
					} else {
						while(a != parent[a]) {
							int a1 = a;
							a = parent[a];
							parent[a1] = b_root;
						}
						parent[a] = b_root;
						size[b_root] += size[a_root];
					}
					disjoint--;
				}
			}
		}
		for(int i = q - 1; i >= 0; i--) {
			int a = roads[quakes[i]-1][0];
			int b = roads[quakes[i]-1][1];
			int a_root = a, b_root = b;
			while(a_root != parent[a_root])
				a_root = parent[a_root];
			while(b_root != parent[b_root])
				b_root = parent[b_root];
			if(a_root != b_root) {
				disjoint--;
				if(disjoint == 1) {
					for(int j = 0; j < i; j++)
						res[j] = true;
					break;
				}
				if(size[a_root] >= size[b_root]) {
					while(b != parent[b]) {
						int b1 = b;
						b = parent[b];
						parent[b1] = a_root;
					}
					parent[b] = a_root;
					size[a_root] += size[b_root];
				} else {
					while(a != parent[a]) {
						int a1 = a;
						a = parent[a];
						parent[a1] = b_root;
					}
					parent[a] = b_root;
					size[b_root] += size[a_root];
				}
			} else
				if(disjoint == 1)
					res[i] = true;
		}
		for(int i = 0; i < q; i++){
			if(res[i])
				bw.write("1");
			else
				bw.write("0");
		}
		bw.close();
	}
}
