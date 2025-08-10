

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
    int u, v, w;

    public Edge(int u, int v, int w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    @Override
    public int compareTo(Edge o) {
        return this.w - o.w; // 가중치를 기준으로 오름차순 정렬
    }
}

public class Main {
    static PriorityQueue<Edge> points; // 간선을 저장할 우선순위 큐 (가중치 기준)
    static int[] p; // 부모 노드 정보 (Union-Find)
    static int[] r; // 각 노드의 랭크 (트리 깊이 조절)
    static int V, E; // V: 정점 개수, E: 간선 개수
    static long min; // 최소 신장 트리의 총 가중치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        points = new PriorityQueue<>();
        min = 0L;

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            points.offer(new Edge(s, e, w)); // 간선을 우선순위 큐에 추가
        }

        makeSet(); // 유니온-파인드 초기화
        int cnt = 0;

        while (cnt != V - 1) { // MST의 간선 개수는 V-1개
            Edge edge = points.poll(); // 가중치가 가장 작은 간선 선택
            if (union(edge.u, edge.v)) { // 사이클이 발생하지 않으면 추가
                cnt++;
                min += edge.w;
            }
        }

        System.out.println(min); // 최소 신장 트리의 총 가중치 출력
    }

    // 유니온-파인드: 두 집합을 합치는 연산
    private static boolean union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y)
            return false; // 같은 집합이면 합치지 않음 (사이클 방지)

        if (r[x] < r[y]) {
            p[x] = y;
            r[y] += r[x];
        } else {
            p[y] = x;
            r[x] += r[y];
        }
        return true;
    }

    // 유니온-파인드: 루트 노드 찾기 (경로 압축)
    private static int find(int x) {
        if (x == p[x])
            return x; // 루트 노드 반환
        return p[x] = find(p[x]); // 경로 압축 적용
    }

    // 유니온-파인드 초기화 (각 노드는 자기 자신이 대표)
    private static void makeSet() {
        p = new int[V + 1];
        r = new int[V + 1];

        for (int i = 0; i <= V; i++) {
            p[i] = i;
            r[i] = 1;
        }
    }
}
