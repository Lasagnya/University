public class Main {
	double [] pair;
	final double EPSILON = 0.0000001;
	public double findY(double x,double y){
		return 1.5 + x - Math.exp(x * y);
	}
	public double findX(double x,double y){
		return Math.pow(2 - y * y, 0.5) - 1;
	}
	public double norma(double [] newNorma){
		double[] norma = newNorma;
		double[] oldNorma = pair;
		if(Math.abs(norma[0] - oldNorma[0]) > Math.abs(norma[1] - oldNorma[1])){
			return Math.abs(norma[0] - oldNorma[0]);
		}
		else{
			return Math.abs(norma[1] - oldNorma[1]);
		}
	}
	public double nevyazka1 (double x,double y){
		double func = Math.exp(x*y) - x + y -1.5;
		return func;
	}
	public double nevyazka2 (double x, double y){
		double func = Math.pow(x+1,2) + Math.pow(y,2) - 2;
		return func;
	}
	public static void main(String[] args) {
		Main main = new Main();
		int i;
		main.pair = new double [2];
		main.pair[0] = 0.3;
		main.pair[1] = 0.6;
		double[] pair2 = new double[2];
		pair2[0] = main.findX(main.pair[0],main.pair[1]);
		pair2[1] = main.findY(pair2[0], main.pair[1]);
		for(i = 1; main.norma(pair2) > main.EPSILON;i++){
			main.pair[0] = pair2[0];
			main.pair[1] = pair2[1];
			pair2[0] = main.findX(main.pair[0],main.pair[1]);
			pair2[1] = main.findY(pair2[0], main.pair[1]);
		}
		System.out.println("x = " + pair2[0] + " y = " + pair2[1]);
		System.out.println("Количество итераций: " + i);
		System.out.println("Невязка первого уравнения = " + main.nevyazka1(pair2[0],pair2[1]));
		System.out.println("Невязка второго уравнения = " + main.nevyazka2(pair2[0],pair2[1]));

	}
}
