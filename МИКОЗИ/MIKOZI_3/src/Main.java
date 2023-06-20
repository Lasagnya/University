import java.util.*;

public class Main {
	public static void main(String[] args) {
		LinkedList<Boolean> a1 = new LinkedList<>(Arrays.asList(false, true, true, false, true));
		LinkedList<Boolean> a2 = new LinkedList<>(Arrays.asList(true, true, false, true, true, true, true));
		LinkedList<Boolean> a3 = new LinkedList<>(Arrays.asList(false, false, false, false, false, false, true, true));
		List<Boolean> c1 = Arrays.asList(true, false, true, true, true);
		List<Boolean> c2 = Arrays.asList(true, true, false,  false,  false,  false, true);
		List<Boolean> c3 = Arrays.asList(true, true, false, false, true, true, false, true);
		LinkedList<Boolean> s1 = LFSR(a1, c1);
		LinkedList<Boolean> s2 = LFSR(a2, c2);
		LinkedList<Boolean> s3 = LFSR(a3, c3);
		List<Boolean> y = geffe(s1, s2, s3);
		System.out.println();
		System.out.println("y: " + y);

		long one_count = y.stream().filter(e -> e).count();
		long zero_count = 10_000l - one_count;
		System.out.println("Нули: " + zero_count);
		System.out.println("Единицы: " + one_count);

		for(int i = 1; i <= 5; i++) {
			int r = 0;
			for(int j = 0; j < 10_000-i; j++) {
				int x = y.get(j) ^ y.get(j+1) ? 1 : 0;
				int tau = (int) Math.pow(-1, x);
				r += tau;
			}
			System.out.println("r_" + i + ": " + r);
		}
	}

	static List<Boolean> geffe(LinkedList<Boolean> s1, LinkedList<Boolean> s2, LinkedList<Boolean> s3) {
		List<Boolean> y = new ArrayList<>();
		ArrayList<Boolean> s1_new = new ArrayList<>(s1);
		ArrayList<Boolean> s2_new = new ArrayList<>(s2);
		ArrayList<Boolean> s3_new = new ArrayList<>(s3);
		for(int i = 0; i < 10_000; i++) {
			Boolean y_t = (s1_new.get(i) & s2_new.get(i)) ^ (!s1_new.get(i) & s3_new.get(i));
			y.add(y_t);
		}
		return y;
	}

	static LinkedList<Boolean> LFSR(LinkedList<Boolean> a, List<Boolean> c) {
		LinkedList<Boolean> s = new LinkedList<>();
		boolean check = true;
		Set<LinkedList<Boolean>> period = new HashSet<>();
		period.add(a);
		int i = 0;
		for(int t = 1; i < 10_000; i++, t++) {
			boolean r = false;
			for(int j = 0; j < a.size(); j++)
				r ^= a.get(j)&c.get(j);
			s.add(a.pollLast());
			a.addFirst(r);
			if(check && period.contains(a)) {
				System.out.println("period: " + t);
				System.out.println("s: " + s);
				break;
			}
		}
		for(; i < 10_000; i++) {
			boolean r = false;
			for(int j = 0; j < a.size(); j++)
				r ^= a.get(j)&c.get(j);
			s.add(a.pollLast());
			a.addFirst(r);
		}
		return s;
	}
}