import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int result;
    static int[][] map;

    static final int[] dr = {0, 0, -1, 1};
    static final int[] dc = {1, -1, 0, 0};

    static class Point {
        int r, c;
        Point(int r, int c) { this.r = r; this.c = c; }
    }

    static ArrayList<Point> viruses = new ArrayList<>();
    static ArrayList<Point> empties = new ArrayList<>();
    static int totalEmpty;          // 초기 빈칸(0) 개수

    // (2) visited 스탬프 방식
    static int[][] vis;
    static int stamp;

    // 큐도 재사용
    static ArrayDeque<Point> q = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        vis = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int v = Integer.parseInt(st.nextToken());
                map[i][j] = v;

                if (v == 2) viruses.add(new Point(i, j));
                else if (v == 0) {
                    totalEmpty++;
                    empties.add(new Point(i, j));
                }
            }
        }

        combi(0, 0);

        System.out.println(result);
    }

    // (1) 빈칸 리스트(empties)에서만 3개 조합
    private static void combi(int start, int depth) {
        if (depth == 3) {
            bfsAndUpdate();
            return;
        }

        for (int i = start; i < empties.size(); i++) {
            Point p = empties.get(i);

            map[p.r][p.c] = 1;          // 벽 세움
            combi(i + 1, depth + 1);
            map[p.r][p.c] = 0;          // 복구
        }
    }

    private static void bfsAndUpdate() {
        stamp++;
        q.clear();

        // 멀티 소스 BFS 시작
        for (Point v : viruses) {
            q.offer(v);
            vis[v.r][v.c] = stamp;
        }

        int infectedEmpty = 0; // 새로 감염된 빈칸(0) 수

        while (!q.isEmpty()) {
            Point cur = q.poll();

            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (vis[nr][nc] == stamp) continue;
                if (map[nr][nc] == 1) continue; // 벽

                vis[nr][nc] = stamp;

                if (map[nr][nc] == 0) infectedEmpty++;

                q.offer(new Point(nr, nc));
            }
        }

        // 초기 빈칸(totalEmpty) 중 3개는 벽이 되었으니 -3
        int safe = (totalEmpty - 3) - infectedEmpty;
        if (safe > result) result = safe;
    }
}
