import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int T, N, M;
    static int[] indeg, dp;

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
    static StringBuilder sb= new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            dp = new int[N + 1];
            indeg = new int[N + 1];
            list = new ArrayList[N + 1];
            for (int j = 0; j < N + 1; j++) {
                list[j] = new ArrayList<>();
            }
            for (int j = 1; j < N + 1; j++) {
                dp[j] = Integer.parseInt(st.nextToken());
            }
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                list[s].add(new Edge(e, dp[e]));
                indeg[e]++;
            }
            int finish = Integer.parseInt(br.readLine());
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            for (int j = 1; j < N + 1; j++) {
                if (indeg[j] == 0) {
                    pq.add(new Edge(j, dp[j]));
                }
            }

            while (!pq.isEmpty()) {
                Edge cur = pq.poll();
                if(cur.e == finish){
                    break;
                }
                for (Edge edge : list[cur.e]) {
                    if (dp[edge.e] < dp[cur.e] + edge.w) {
                        dp[edge.e] = dp[cur.e] + edge.w;
                    }
                    if(--indeg[edge.e] == 0 ){
                        pq.add(edge);
                    }
                }
            }
            sb.append(dp[finish]).append("\n");
            // System.out.println(dp[finish]);
        }
        System.out.println(sb);
    }
}
