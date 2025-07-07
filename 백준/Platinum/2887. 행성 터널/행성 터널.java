import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
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

    static class Planet {
        int x, y, z, idx;

        public Planet(int x, int y, int z, int idx) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.idx = idx;
        }
    }

    static PriorityQueue<Edge> points = new PriorityQueue<>();
    static ArrayList<Edge>[] list;
    static boolean[] visited;
    static long result; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] memo = new int[N][3];
        Planet[] planets = new Planet[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            memo[i][0] = Integer.parseInt(st.nextToken());
            memo[i][1] = Integer.parseInt(st.nextToken());
            memo[i][2] = Integer.parseInt(st.nextToken());
            planets[i] = new Planet(memo[i][0], memo[i][1], memo[i][2], i);
        }

        list = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            list[i] = new ArrayList<>();
        }
        Arrays.sort(planets, (a, b) -> Integer.compare(a.x, b.x));
        for (int i = 0; i < N - 1; i++) {
            int w = Math.abs(planets[i].x - planets[i + 1].x);
            list[planets[i].idx].add(new Edge(planets[i + 1].idx, w));
            list[planets[i + 1].idx].add(new Edge(planets[i].idx, w));
        }

        Arrays.sort(planets, (a, b) -> Integer.compare(a.y, b.y));
        for (int i = 0; i < N - 1; i++) {
            int w = Math.abs(planets[i].y - planets[i + 1].y);
            list[planets[i].idx].add(new Edge(planets[i + 1].idx, w));
            list[planets[i + 1].idx].add(new Edge(planets[i].idx, w));
        }

        Arrays.sort(planets, (a, b) -> Integer.compare(a.z, b.z));
        for (int i = 0; i < N - 1; i++) {
            int w = Math.abs(planets[i].z - planets[i + 1].z);
            list[planets[i].idx].add(new Edge(planets[i + 1].idx, w));
            list[planets[i + 1].idx].add(new Edge(planets[i].idx, w));
        }

        int cnt = 0;
        points = new PriorityQueue<>();
        points.add(new Edge(0, 0));
        visited = new boolean[N];

        while (cnt != N) {
            Edge tmp = points.poll();
            if (visited[tmp.e]) {
                continue;
            }
            visited[tmp.e] = true;
            cnt++;
            result += tmp.w;

            for (Edge next : list[tmp.e]) {
                if (visited[next.e]) {
                    continue;
                }
                points.add(next);
            }
        }

        System.out.println(result);
    }
}