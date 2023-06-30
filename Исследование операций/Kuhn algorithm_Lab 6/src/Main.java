import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int n;                                       //кол-во вершин в первой доле
    static int k;                                       //кол-во вершин во второй доле
    static List<List<Integer>> g = new ArrayList<>();   //список рёбер из вершин первой доли
    static int[] mt;                                    //с какой вершиной первой доли связана вершина i
    static boolean[] used;                              //массив посещённости для dfs

    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new BufferedReader(new FileReader("input.txt")));
        st.nextToken();
        n = (int) st.nval;
        st.nextToken();
        k = (int) st.nval;
        for (int i = 0; i < n; i++) {
            st.nextToken();
            List<Integer> g_ = new ArrayList<>();
            while (st.nval != 0) {
                g_.add((int) st.nval-1);
                st.nextToken();
            }
            g.add(g_);
        }
        mt = new int[k];
        Arrays.fill(mt, -1);
        used = new boolean[n];

        int count = 0;
        for (int v = 0; v < n; v++) {       //проходим dfs из каждой вершины и ищем увеличивающиеся цепи
            Arrays.fill(used, false);   //по какой-то причине работает также, если убрать очищение, надо найти контрпример
            if (dfs(v))
                count++;
        }
        System.out.println(count);
    }

    static boolean dfs(int v) {
        if (used[v])
            return false;
        used[v] = true;
        for (Integer to: g.get(v)) {
            if (mt[to] == -1 || dfs(mt[to])) {
                mt[to] = v;
                return true;
            }
        }
        return false;
    }
}