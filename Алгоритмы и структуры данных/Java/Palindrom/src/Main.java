import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
		st.nextToken();
		String str = st.sval;
		int[][] matr = new int[str.length()][str.length()];
		for(int i = 0; i < str.length(); i++)
			matr[i][i] = 1;
		for(int j = 1; j < str.length(); j++) {
			for(int i = j - 1; i >= 0; i--) {
				if(j-i == 1) {
					if(str.charAt(i) == str.charAt(j))
						matr[i][j] = 2;
					else
						matr[i][j] = 1;
				} else {
					if(str.charAt(i) == str.charAt(j))
						matr[i][j] = matr[i+1][j-1] + 2;
					else {
						if(matr[i+1][j] >= matr[i][j-1])
							matr[i][j] = matr[i+1][j];
						else
							matr[i][j] = matr[i][j-1];
					}
				}
			}
		}

		int j = str.length()-1;
		int i = 0;
		StringBuilder sb = new StringBuilder();
		String palindrom = "";
		while(i < j) {
			if(str.charAt(i) == str.charAt(j)) {
				sb.append(str.charAt(i));
				++i;
				--j;
			} else {
				if(matr[i+1][j] >= matr[i][j-1])
					++i;
				else
					--j;
			}
		}
		if(i == j) {
			palindrom = sb.toString() + str.charAt(i) + sb.reverse().toString();
		} else {
			palindrom = sb.toString() + sb.reverse().toString();
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		bw.write(palindrom.length() + "\n");
		bw.write(palindrom);
		bw.close();
	}
}
