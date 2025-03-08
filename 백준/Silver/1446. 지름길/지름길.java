
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Edge implements Comparable<Edge> {
        int to, cost;

        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(cost, o.cost);
        }
    }

    static List<Edge>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        graph = new List[D + 1];
        for (int i = 0; i < D + 1; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < D; i++) {
            graph[i].add(new Edge(i + 1, 1));
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (e <= D && cost < (e - s)) {
                graph[s].add(new Edge(e, cost));
            }
        }

        int[] dist = new int[D + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(0, 0));

        while (!pq.isEmpty()) {
            Edge tmp = pq.poll();
            int tmpNode = tmp.to;
            int tmpCost = tmp.cost;

            if (tmpCost > dist[tmpNode])
                continue;

            // System.out.println(Arrays.toString(dist));
            for (Edge next : graph[tmpNode]) {
                if (dist[next.to] > dist[tmpNode] + next.cost) {
                    dist[next.to] = dist[tmpNode] + next.cost;
                    pq.add(new Edge(next.to, dist[next.to]));
                }
            }
        }
        System.out.println(dist[D]);
    }
}
