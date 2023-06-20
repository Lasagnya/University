import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		st.nextToken();
		int n = (int) st.nval;
		st.nextToken();
		int q = (int) st.nval;
		int[] parent = new int[n+1];
		int[] size = new int[n+1];
		for(int i = 1; i < n + 1; i++) {
			parent[i] = i;
			size[i] = 1;
		}
		int disjoint = n;
		for(int i = 0; i < q; i++) {
			st.nextToken();
			int a = (int) st.nval;
			st.nextToken();
			int b = (int) st.nval;
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
			bw.write(String.valueOf(disjoint) + "\n");
		}
		bw.close();
	}
}
