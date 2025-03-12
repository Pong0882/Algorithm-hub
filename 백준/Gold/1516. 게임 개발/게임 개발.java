
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<Integer>[] list;
    static int[] indeg;
    static StringBuilder sb = new StringBuilder();
    static int[] result;
    static int[] paper;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        indeg = new int[N + 1];
        result = new int[N + 1];
        paper = new int[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());

            int n = st.countTokens();
            for (int j = 0; j < n - 1; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (j == 0) {
                    result[i] = tmp;
                    paper[i] = tmp;
                } else {
                    indeg[i]++;
                    list[tmp].add(i);
                }
            }
        }
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i < N + 1; i++) {
            if (indeg[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int next : list[cur]) {
                result[next] = Math.max(result[next], result[cur] + paper[next]);
                if (--indeg[next] == 0) {
                    q.add(next);
                }
            }
        }
        for (int i = 1; i < N + 1; i++) {
            sb.append(result[i]).append("\n");
        }
        System.out.println(sb);
    }
}
