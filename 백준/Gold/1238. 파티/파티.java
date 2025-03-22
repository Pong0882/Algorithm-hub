import java.io.*;
import java.util.*;
// GPT 코드
public class Main {
    static int N, M, dest;
    static ArrayList<Edge>[] graph, reverseGraph;
    
    static class Edge implements Comparable<Edge> {
        int to, cost;
        public Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
        public int compareTo(Edge other) {
            return Integer.compare(this.cost, other.cost);
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[N + 1];
        reverseGraph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
            reverseGraph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph[from].add(new Edge(to, cost));
            reverseGraph[to].add(new Edge(from, cost)); 
        }
        
        int[] distanceFrom = dijkstra(graph, dest);
        int[] distanceTo = dijkstra(reverseGraph, dest);
        
        int result = 0;
        for (int i = 1; i <= N; i++) {
            result = Math.max(result, distanceFrom[i] + distanceTo[i]);
        }
        System.out.println(result);
    }
    
    static int[] dijkstra(ArrayList<Edge>[] g, int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        dist[start] = 0;
        pq.add(new Edge(start, 0));
        
        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int cur = current.to;
            int cost = current.cost;
            if (dist[cur] < cost) continue;
            
            for (Edge next : g[cur]) {
                int newCost = cost + next.cost;
                if (dist[next.to] > newCost) {
                    dist[next.to] = newCost;
                    pq.add(new Edge(next.to, newCost));
                }
            }
        }
        return dist;
    }
}
