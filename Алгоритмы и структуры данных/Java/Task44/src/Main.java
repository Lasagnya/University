import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
		st.nextToken();
		int n = (int) st.nval;
		int result = func(n);
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		bw.write(String.valueOf(result));
		bw.close();
	}

	static int func(int n) {
		if(n <= 3)
			return 1;
		else if(n % 2 != 0)
			return func(n/2) + func(n/2 + 1);
		else
			return 2 * func(n/2);
	}
}
