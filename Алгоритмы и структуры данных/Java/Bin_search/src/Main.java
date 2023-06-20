import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException{
		int n, k;
		int[] arr;
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
		st.nextToken();
		n = (int) st.nval;
		arr = new int[n];
		for(int i = 0; i<n; i++) {
			st.nextToken();
			arr[i] = (int) st.nval;
		}
		st.nextToken();
		k = (int) st.nval;
		for(int i = 0; i<k; i++) {
			st.nextToken();
			System.out.println(binSearch(arr, (int) st.nval));
		}
	}

	static String binSearch(int[] arr, int x) {
		int low = 0, l = 0;
		int high = arr.length, h = arr.length;
		int mid = 0, m = 0;
		while (low < high) {
			mid = (low + high) / 2;
			if (arr[mid] < x) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}

		if(low < arr.length && arr[low] == x) {
			while (l < h) {
				m = (l + h) / 2;
				if (arr[m] > x) {
					h = m;
				} else {
					l = m + 1;
				}
			}
			return "1 " + low + " " + h;
		} else {
			return "0 " + low + " " + low;
		}
	}
}
