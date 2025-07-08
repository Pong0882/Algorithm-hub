import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int T, result;
    static int[] map;
    static boolean[] visited;
    static boolean[] finished;
    static int End;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(st.nextToken());

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine());

            map = new int[n + 1];
            finished = new boolean[n + 1];
            visited = new boolean[n + 1];
            result = n;

            st = new StringTokenizer(br.readLine());

            for (int i = 1; i <= n; i++) {
                map[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i <= n; i++) {
                if (!visited[i]) {
                    DFS(i);
                }
            }
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static void DFS(int i) {
        visited[i] = true;
        int next = map[i];

        if (!visited[next]) {
            DFS(next);
        } else if (!finished[next]) {
            for (int j = next; j != i; j = map[j]) {
                result--;
            }
            result--;
        }
        finished[i] = true;
    }
}
