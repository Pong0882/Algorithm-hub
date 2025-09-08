import java.util.*;
import java.io.*;
import java.lang.reflect.Array;

public class Main {
    static class Edge implements Comparable<Edge> {
        int e, w;

        public Edge(int e, int w) {
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.w, o.w);
        }
    }

    static int V, E;
    static ArrayList<Edge>[] g;
    static StringBuilder sb = new StringBuilder();
    static int[] result;
    static final int INF = 100_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        g = new ArrayList[V + 1];
        result = new int[V + 1];
        for (int i = 1; i < V + 1; i++) {
            g[i] = new ArrayList<>();
            result[i] = INF;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int start = Integer.parseInt(br.readLine());
        pq.add(new Edge(start, 0));
        result[start] = 0;

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            g[s].add(new Edge(e, w));
        }

        // 다익스트라
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.w > result[cur.e]) {
                continue;
            }

            for (Edge nextNode : g[cur.e]) {
                int nextW = nextNode.w + cur.w;
                if (nextW >= result[nextNode.e]) {
                    continue;
                }
                pq.add(new Edge(nextNode.e, nextW));
                result[nextNode.e] = nextW;
            }
        }

        // System.out.println(Arrays.toString(result));

        for (int i = 1; i < V + 1; i++) {
            sb.append(result[i] == INF ? "INF" : result[i]);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}