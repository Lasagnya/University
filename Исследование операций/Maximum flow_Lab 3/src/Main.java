import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException {
		StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
		st.nextToken();
		int n = (int) st.nval;
		st.nextToken();
		int[][] matrix = new int[n][n];
		for(int i = 0; i < n; i++) {
			st.nextToken();
			while (st.nval != 0) {
				matrix[i][(int)st.nval - 1] = 1;
				st.nextToken();
			}
		}
		st.nextToken();
		int v = (int) st.nval;
		st.nextToken();
		int w = (int) st.nval;

		int flow = 0;
		int[] edges = Dijkstra(matrix, v-1);				//ищем маршрут (оптимальный, но можно любой)
		while(edges[w-1] != w-1) {								//повторяем, пока есть путь
			List<Integer> path = new ArrayList<>();
			path.add(v - 1);    								//добавляем начальную вершину
			int previous = w - 1;    							//находим путь с конца
			while (previous != v - 1) {
				path.add(1, previous);
				previous = edges[previous];
			}
			int minvalue = matrix[path.get(0)][path.get(1)];
			for (int i = 1; i < path.size() - 1; i++) {					//ищем минимальный допуск
				if (matrix[path.get(i)][path.get(i + 1)] < minvalue)
					minvalue = matrix[path.get(i)][path.get(i + 1)];
			}
			for (int i = 0; i < path.size()-1; i++) {					//отнимаем минимальный допуск от дуги
				matrix[path.get(i)][path.get(i + 1)] -= minvalue;		//и прибавляем к обратной дуге
				matrix[path.get(i + 1)][path.get(i)] += minvalue;
			}
			flow += minvalue;											//ширина потока - сумма минимальных допусков
			edges = Dijkstra(matrix, v - 1);
		}
		System.out.println("Maximum flow " + flow);
		BufferedWriter bw = new BufferedWriter(new FileWriter("output.txt"));
		bw.write(Integer.toString(flow));
		bw.close();
	}

	static int[] Dijkstra(int[][] matrix, int begin){	//begin - номер с началом от 0!!!!!
		int n = matrix.length;
		boolean[] visited = new boolean[n];				//помечать посещённые вершины
		int[] distance = new int[n];					//помечать расстояние от begin до вершины
		int[] path = new int[n];						//помечать путь
		for (int i = 0; i < n; i++) {
			visited[i] = false;
			distance[i] = Integer.MAX_VALUE;
			path[i] = i;
		}
		distance[begin] = 0;

		int current = begin;					//рассматриваемая вершина
		while (current != -1){
			for(int i = 0; i < n; i++) {
				if(matrix[current][i] != 0 && distance[current]+matrix[current][i] < distance[i]) {
					distance[i] = distance[current] + matrix[current][i];
					path[i] = current;					//помечаем, из какой вершины будет минимальное расстояние
				}
			}
			visited[current] = true;
			current = -1;
			int mindistance = Integer.MAX_VALUE;
			for (int i = 0; i < n; i++)
				if(!visited[i] && distance[i] < mindistance) {			//выбираем перебором вершину с минимальной дистанцией
					current = i;
					mindistance = distance[i];
				}
		}
		return path;
	}
}