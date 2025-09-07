import java.util.*;
import java.io.*;

public class Main {
    static class Edge implements Comparable<Edge> {
        int e, w;
        
        Edge(int e, int w){
            this.e = e;
            this.w = w;
        }
        
        @Override public int compareTo(Edge o){
            return Integer.compare(this.w, o.w);
        }
    }
     
    static ArrayList<Edge>[] g;
    static int M, S;
    static int MV, SV;
    static int[][] paper;
    static final int MAX_VAL = 100_100_100;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        
        paper = new int[V+1][2];
        
        g = new ArrayList[V+1];
        for( int i = 1 ; i <= V ; i++){
            g[i] = new ArrayList<>();
            paper[i][0] = MAX_VAL;
            paper[i][1] = MAX_VAL;
        }
        
        for(int i = 0 ; i < E ; i ++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            g[s].add(new Edge(e,w));
            g[e].add(new Edge(s,w));
        }
        
        // 맥도날드
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        MV = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int s = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(s, 0));
            paper[s][0] = 0;
        }

        while (!pq.isEmpty()) {
            Edge tmp = pq.poll();
            int curNode = tmp.e;
            int curW = tmp.w;
            if (curW > paper[curNode][0]) continue;

            for (Edge nextEdge : g[curNode]) {
                int nextNode = nextEdge.e;
                int nextW = nextEdge.w + curW;
                if (nextW > MV) continue;
                if (nextW < paper[nextNode][0]) {
                    paper[nextNode][0] = nextW;
                    pq.offer(new Edge(nextNode, nextW));
                }
            }
        }

        // 스타벅스
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        SV = Integer.parseInt(st.nextToken());

        pq.clear();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            int s = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(s, 0));
            paper[s][1] = 0;
        }

        while (!pq.isEmpty()) {
            Edge tmp = pq.poll();
            int curNode = tmp.e;
            int curW = tmp.w;
            if (curW > paper[curNode][1]) continue;

            for (Edge nextEdge : g[curNode]) {
                int nextNode = nextEdge.e;
                int nextW = nextEdge.w + curW;
                if (nextW > SV) continue;
                if (nextW < paper[nextNode][1]) {
                    paper[nextNode][1] = nextW;
                    pq.offer(new Edge(nextNode, nextW));
                }
            }
        }

        int ans = MAX_VAL;
        for (int v = 1; v <= V; v++) {
            if (paper[v][0] == 0 || paper[v][1] == 0) continue;
            if (paper[v][0] <= MV && paper[v][1] <= SV) {
                ans = Math.min(ans, paper[v][0] + paper[v][1]);
            }
        }
        System.out.println(ans == MAX_VAL ? -1 : ans);
    }
}