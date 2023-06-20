public class Main {
	public static double[][] reverse;
	static double[] func;
	final double EPSILON = 0.0000001;

	void findFunc(double[] a) {
		func[0] = Math.exp(a[0]*a[1]) - a[0] + a[1] - 1.5;
		func[1] = Math.pow((a[0] + 1), 2) + Math.pow(a[1], 2) - 2;
	}

	void solve() {
		double[] x0 = new double[2];
		double[] x1  = new double[2];
		func = new double[2];
		x1[0] = 0.3;
		x1[1] = 0.6;
		int i = 0;
		x0[0] = x1[0];
		x0[1] = x1[1];
		findFunc(x0);
		x1[0] = x0[0] - (reverse[0][0]*func[0]+reverse[0][1]*func[1]);
		x1[1] = x0[1] - (reverse[1][0]*func[0]+reverse[1][1]*func[1]);
		while(EPSILON < Math.max(Math.abs(x1[0]-x0[0]),Math.abs(x1[1]-x0[1]))) {
			x0[0] = x1[0];
			x0[1] = x1[1];
			findFunc(x0);
			x1[0] =x0[0] - (reverse[0][0]*func[0]+reverse[0][1]*func[1]);
			x1[1] =x0[1] - (reverse[1][0]*func[0]+reverse[1][1]*func[1]);
			i++;
		}
		System.out.println("Решение: (" + x1[0] + " , " + x1[1] + ")");
		System.out.println("Количество итераций: " + i);
		findFunc(x1);
		System.out.println("Невязка первого уравнения: " + func[0]);
		System.out.println("Невязка второго уравнения: " + func[1]);
	}

	public static void main(String[] args) {
		reverse = new double [2][2];
		reverse[0][0] = -0.30993;
		reverse[0][1] = 0.35104;
		reverse[1][0] = 0.67151;
		reverse[1][1] = 0.07275;
		Main ma = new Main();
		ma.solve();
	}
}
