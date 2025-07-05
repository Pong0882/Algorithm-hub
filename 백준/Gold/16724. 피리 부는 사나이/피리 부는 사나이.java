import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] parent;

    // 방향: U R D L ➜ dr/dc
    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };
    static char[] dir = { 'U', 'R', 'D', 'L' };

    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        parent = new int[N * M];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // 유니온 파인드 초기화
        for (int i = 0; i < N * M; i++) {
            parent[i] = i;
        }

        // 각 칸에서 방향대로 union
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < M; c++) {
                int dirIdx = getDirIdx(map[r][c]);
                int nr = r + dr[dirIdx];
                int nc = c + dc[dirIdx];

                int cur = r * M + c;
                int next = nr * M + nc;

                union(cur, next);
            }
        }

        // 최종 싸이클 개수 세기
        int result = 0;
        for (int i = 0; i < N * M; i++) {
            if (parent[i] == i) result++;
        }

        System.out.println(result);
    }

    // 방향 문자 → 인덱스
    static int getDirIdx(char d) {
        for (int i = 0; i < 4; i++) {
            if (dir[i] == d) return i;
        }
        return -1; // 안올 상황
    }

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // 경로 압축
        }
        return parent[x];
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (pa != pb) {
            parent[pa] = pb;
        }
    }
}
