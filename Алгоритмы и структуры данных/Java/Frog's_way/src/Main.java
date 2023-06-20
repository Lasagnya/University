import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
			StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));	//new FileReader("input.txt")	new InputStreamReader(System.in)
		st.nextToken();
		int n = (int) st.nval;
		int[] value = new int[n];
		int[] sum = new int[n];
		for(int i = 0; i < n; i++) {
			st.nextToken();
			value[i] = (int) st.nval;
			sum[i] = Integer.MIN_VALUE;
		}
		sum[0] = value[0];
		for(int i = 0; i < n; i++) {
			if(i+2 < n) {
				int i2 = sum[i] + value[i + 2];
				if(i2 > sum[i + 2])
					sum[i + 2] = i2;
			}
			if(i+3 < n) {
				int i3 = sum[i] + value[i + 3];
				if(i3 > sum[i + 3])
					sum[i + 3] = i3;
			}
		}

		if(sum[n-1] != Integer.MIN_VALUE) {
			System.out.println(sum[n-1]);
//			int[] way = new int[n];
//			way[0] = n;
//			int count = 1;
//			int i = n - 1;
//			while(i != 0) {
//				if(sum[i-2] == sum[i] - value[i]) {
//					way[count] = i - 1;
//					++count;
//					i -= 2;
//				} else {
//					way[count] = i - 2;
//					++count;
//					i -= 3;
//				}
//			}
//			for(int j = count-1; j >= 0; j--) {
//				System.out.print(way[j]);
//				System.out.print(" ");
//			}


			int i = n-1;
			String ways = "";
			while(i > 0) {
				ways = " " + (i+1) +  ways;
				if(sum[i] - value[i] == sum[i-2])
					i -= 2;
				else
					i -= 3;
			}
			ways = "1" + ways;
			System.out.print(ways);
		}
		else
			System.out.print("-1");
	}
}
