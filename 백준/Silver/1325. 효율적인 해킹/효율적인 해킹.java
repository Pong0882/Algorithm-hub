import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static List<Integer>[] list;

    private static int[] result;
    // private static boolean[] visited;
    private static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];

        for (int i = 1; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[b].add(a);
        }

        result = new int[N + 1];
        visited = new int[N + 1];
        int max = 0;

        for (int i = 1; i < N + 1; i++) {
            if (list[i].isEmpty()) {
                continue;
            }
            Queue<Integer> que = new ArrayDeque<>();

            visited[i] = i;
            int cnt = 0;
            que.offer(i);
            while (!que.isEmpty()) {

                int cur = que.poll();
                for (int j = 0; j < list[cur].size(); j++) {
                    int next = list[cur].get(j);
                    if (visited[next] != i) {
                        visited[next] = i;
                        que.offer(next);
                        cnt++;
                    }
                }
            }
            result[i] = cnt;
            max = Math.max(max, cnt);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            if (result[i] == max) {
                sb.append(i).append(" ");
            }
        }
        System.out.println(sb);
    }
}
