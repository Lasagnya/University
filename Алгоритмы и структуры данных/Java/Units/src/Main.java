import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));	//new FileReader("input.txt")	new InputStreamReader(System.in)
		st.nextToken();
		int n = (int) st.nval;
		st.nextToken();
		int k = (int) st.nval;
		long result = placement(n, k);
		System.out.println(result);
	}

	static long placement(int n, int k) {
		long result;
		if(k >= n/2) {
			long a = 1;
			for(int i = k+1; i <= n; i++) {
				a = (a * i) % 1000000007;
			}
			long b = 1;
			for(int i = 1; i <= n-k; i++) {
				b = (b * i) % 1000000007;
			}
			long c = binPow(b, 1000000005, 1000000007);
			result = (a * c) % 1000000007;
		} else {
			long a = 1;
			for(int i = n-k+1; i <= n; i++) {
				a = (a * i) % 1000000007;
			}
			long b = 1;
			for(int i = 1; i <= k; i++) {
				b = (b * i) % 1000000007;
			}
			long c = binPow(b, 1000000005, 1000000007);
			result = (a * c) % 1000000007;
		}
		return result;
	}

	public static long binPow(long a, long b, int m) {
		a %= m;
		if (b == 0) return 1;
		else if (b % 2 == 0) {
			return binPow((a * a) % m, b / 2, m);
		}
		else return (a * binPow(a, b - 1, m)) % m;
	}
}
