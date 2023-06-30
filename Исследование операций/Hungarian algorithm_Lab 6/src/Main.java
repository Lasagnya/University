import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;						//Алгоритм для поиска минимального результата.
import java.io.StreamTokenizer;					//Для максимального надо кое что изменить.
import java.util.Arrays;
												//В целом в будущем можно будет подумать над оптимизацией
public class Main {
	static int n;				//размер матрицы
	static int[][] matrix;
	static int[] mincol;		//веса задач, повышаются на delta у посещённых задач после паросочетания в целях его сохранения
	static int[] maxrow;		//максимальный навык работника, понижается на delta у посещённых работников после паросочетания
	static int[][] pair;		//пары работник - задача
	static boolean[][] way;		//учавствовала ли вершина в текущем построении решения, 0 для работника, 1 для задачи

	public static void main(String[] args) throws IOException {
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
		st.nextToken();
		n = (int) st.nval;
		matrix =  new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				st.nextToken();
				matrix[i][j] = -(int) st.nval;			//Тут убрать минус
			}
		}
		mincol = new int[n];
		Arrays.fill(mincol, 0);
		maxrow = new int[n];
		Arrays.fill(maxrow, Integer.MIN_VALUE);			//Тут поставить 0
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				maxrow[i] = Math.max(maxrow[i], matrix[i][j]);
			}
		}
		pair = new int[n][2];
		for (int[] e : pair)
			Arrays.fill(e, -1);
		way = new boolean[n][2];


		for (int c = 0; c < n;) {						//прекращаем, когда все работники получат задачи
			for (boolean[] e : way)
				Arrays.fill(e, false);
			int k = 0;									//кол-во пар в итерации
			for (int i = 0; i < n; i++) {
				if (pair[i][0] == -1 && kunh(i))		//если у работника нет задачи и можно найти оптимальную задача - образовали пару
					++k;
			}
			c += k;
//			if (k != 0) {
				int delta = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++) {
				if (way[i][0])
					for (int j = 0; j < n; j++)												//ищем delta - минимальная разность между посещённым работником +
						if (!way[j][1])														//+ непосещённой задачей и значением матрицы. По сути нужна,
							delta = Math.min(delta, maxrow[i]+mincol[j]-matrix[i][j]);		//чтобы добавить минимальное кол-во задач в возможные оптимальные,
			}																				//так как текущих оптимальных не хватает.
				for (int i = 0; i < n; i++) {
					if (way[i][0]) maxrow[i] -= delta;						//отнимаем delta от посещённых работников
					if (way[i][1]) mincol[i] += delta;						//прибавляем к посещённым задачам
				}
//			}
		}

		for (int[] e : pair)
			System.out.println(e[0]+1);
	}

	static boolean kunh(int i) {									//ищем паросочетание по оптимальным рёбрам
		if (way[i][0])
			return false;
		way[i][0] = true;											//помечаем работников
		for (int j=0; j<n; ++j) {
			if (matrix[i][j] - maxrow[i] - mincol[j] == 0)			//помечаем все доступные задачи
				way[j][1] = true;
			if (way[j][1] && (pair[j][1] == -1 || kunh(pair[j][1]))) {		//назначаем пару
				pair[i][0] = j;
				pair[j][1] = i;
				return true;
			}
		}
		return false;
	}
}