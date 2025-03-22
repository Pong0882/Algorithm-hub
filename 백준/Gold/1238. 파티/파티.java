import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, desti;

    static class Edge implements Comparable<Edge> {
        int e, w;

        public Edge(int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(w, o.w);
        }
    }

    static ArrayList<Edge>[] list;
    static int[][] dp;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        desti = Integer.parseInt(st.nextToken());

        list = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[s].add(new Edge(e, w));
        }

        dp = new int[N + 1][N + 1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int i = 1; i < N + 1; i++) {
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            dp[i][i] = 0;
            pq.add(new Edge(i, 0));
            while (!pq.isEmpty()) {
                Edge cur = pq.poll();
                for (Edge next : list[cur.e]) {
                    int newCost = dp[i][cur.e] + next.w;
                    if (dp[i][next.e] > newCost) {
                        dp[i][next.e] = newCost;
                        pq.add(new Edge(next.e, newCost));
                    }
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            result = Math.max(result, dp[i][desti] + dp[desti][i]);
        }
        // for (int i = 0; i < N + 1; i++) { // 디버깅
        // System.out.println(Arrays.toString(dp[i]));
        // }
        System.out.println(result);

    }
}
