import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
		st.nextToken();
		int n = (int) st.nval;
		st.nextToken();
		int k = (int) st.nval;
		long stair1 = 0, stair2 = 1, temp;
		for(int i = 0; i < k; i++) {
			temp = stair2;
			stair2 = (stair1 + stair2) % 1000000009;
			stair1 = temp;
		}
		long result = ((stair2 * stair2) % 1000000009) + 1;
		result = binPow(result, n-1, 1000000009);
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		bw.write(String.valueOf(result));
		bw.close();
	}

	public static long binPow(long x, long n, int m) {
		x %= m;
		if (n == 0) return 1;
		else if (n % 2 == 0) {
			return binPow((x * x) % m, n / 2, m);
		}
		else return (x * binPow(x, n - 1, m)) % m;
	}
}