import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
	public static void main(String[] args) {
		String[] alphabet = "0123456789abcdef".split("");
		String[] s1 =       "96328b17a4efc0d5".split("");
		String[] s2 =       "37e98af0526cb4d1".split("");
		List<String> alph = List.of(alphabet);
		int x = 0b0010_0111;
		int k = 0b1101_1111_0000;
		int[] ki = {0b1101_1111, 0b1101_0000, 0b1111_0000};
		int y = x;
		System.out.println("Результат трёх итераций SP-подстановки:");
		for(int i = 0; i < 3; i++) {
			int t = y^ki[i];
			String t1 = Integer.toHexString(t).substring(0, 1);
			String t2 = Integer.toHexString(t).substring(1, 2);
			String n1 = s1[alph.indexOf(t1)];
			String n2 = s2[alph.indexOf(t2)];
			String n = String.format("%8s", Integer.toBinaryString(Integer.parseInt(n1 + n2, 16)))
					.replace(' ', '0');
			List<Character> n_list = n.chars().mapToObj(e -> (char)e).collect(Collectors.toList());
			Collections.rotate(n_list, 5);
			y = Integer.parseInt(n_list.stream().map(e -> e.toString()).collect(Collectors.joining()), 2);
			System.out.println(String.format("%8s", Integer.toBinaryString(y)).replace(' ','0'));
		}
	}
}