
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static long result;

    // static class Edge implements Comparable<Edge> {
    // int e, w;

    // public Edge(int e, int w) {
    // this.e = e;
    // this.w = w;
    // }

    // @Override
    // public int compareTo(Edge o) {
    // return Integer.compare(o.w, w);
    // }

    // }

    static ArrayList<int[]>[] list;
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        long max = 0;
        PriorityQueue<int[]> points = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
        PriorityQueue<int[]> Mpoints = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));
        list = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            list[s].add(new int[] { e, w });
            if (s == 0) {
                continue;
            }
            list[e].add(new int[] { s, w });
        }

        visited = new boolean[N + 1];
        Mpoints.add(new int[] { 0, list[0].get(0)[0] });
        int cnt = 0;
        while (!Mpoints.isEmpty()) {
            if (cnt == N + 1) {
                break;
            }
            int[] tmp = Mpoints.poll();
            if (visited[tmp[0]]) {
                continue;
            }

            visited[tmp[0]] = true;
            if (tmp[1] == 0) {
                max++;
            }
            cnt++;

            for (int[] edge : list[tmp[0]]) {
                if (visited[edge[0]]) {
                    continue;
                }
                Mpoints.add(edge);
            }
        }
        visited = new boolean[N + 1];
        points.add(new int[] { 0, 1 });
        cnt = 0;
        while (!points.isEmpty()) {
            if (cnt == N + 1) {
                break;
            }
            int[] tmp = points.poll();
            if (visited[tmp[0]]) {
                continue;
            }
            visited[tmp[0]] = true;
            if (tmp[1] == 0) {
                result++;
            }
            cnt++;

            for (int[] edge : list[tmp[0]]) {
                if (visited[edge[0]]) {
                    continue;
                }
                points.add(edge);
            }
        }
        System.out.println(result * result - max * max);

    }
}
