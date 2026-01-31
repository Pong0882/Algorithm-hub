import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean[][] map;
    static boolean[][] outside;
    static boolean[][] inCheese;
    static int[] dr = { 0, 0, -1, 1 };
    static int[] dc = { 1, -1, 0, 0 };

    static class Position {
        int r, c;

        public Position(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 치즈 두 면이 공기 닿으면 녹음
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        outside = new boolean[N][M];
        inCheese = new boolean[N][M];
        // 0 공기, 1 치즈
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 1) {// 치즈면
                    map[i][j] = true;
                }
            }
        }

        // 녹은애들 주변만 체크해주면 될거같은데?
        // 처음엔 전체치즈 검사한번하고
        // 외부공기유입도 확인해야하네
        // 처음에 0,0 부터 bfs 돌려야겠고,
        // 내부 공간 만나는거 비지티드로 한번더 체크해서 활용해야겠다

        ArrayDeque<Position> q = new ArrayDeque<>();
        ArrayDeque<Position> cheese = new ArrayDeque<>();
        q.add(new Position(0, 0));
        outside[0][0] = true;
        expandOutsideAndCollectCheese(q, cheese);
        int time = 0;

        while (!cheese.isEmpty()) {
            ArrayDeque<Position> melt = new ArrayDeque<>();

            // 현재 경계 치즈들만 검사해서 녹을 애들 추림
            int size = cheese.size();
            for (int s = 0; s < size; s++) {
                Position cur = cheese.poll();
                inCheese[cur.r][cur.c] = false; // 큐에서 뺐으니 중복방지 해제

                if (!map[cur.r][cur.c])
                    continue; // 이미 녹았을 수 있음

                int touch = 0;
                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];
                    if (isOut(nr, nc)) {
                        continue;
                    }

                    // 외부 공기와 맞닿은 면만 카운트
                    if (!map[nr][nc] && outside[nr][nc]) {
                        touch++;
                    }
                }

                if (touch >= 2) {
                    melt.add(cur);
                } else {
                    // 아직 안 녹으면 다음에 또 확인
                    cheese.add(cur);
                    inCheese[cur.r][cur.c] = true;
                }
            }

            if (melt.isEmpty())
                break;

            // 이번 시간에 녹이기 + 녹은 칸에서 외부 공기 확장
            // 녹은 칸을 q에 넣어서 추가 확장
            while (!melt.isEmpty()) {
                Position p = melt.poll();
                map[p.r][p.c] = false; // 녹음

                if (!outside[p.r][p.c]) {
                    outside[p.r][p.c] = true;
                    q.add(p);
                }
            }

            // 녹은 칸들로 인해 내부 공기가 외부로 열릴 수 있으니,
            // 그 지점부터 다시 외부 공기 BFS 확장 + 새 경계 치즈 수집
            expandOutsideAndCollectCheese(q, cheese);

            time++;
        }

        System.out.println(time);
    }

    // q: 외부 공기 BFS 확장 큐
    // 확장하면서 만나는 치즈는 cheese 큐에 넣기
    private static void expandOutsideAndCollectCheese(ArrayDeque<Position> q, ArrayDeque<Position> cheese) {
        while (!q.isEmpty()) {
            Position cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if (isOut(nr, nc))
                    continue;
                if (outside[nr][nc])
                    continue;

                if (map[nr][nc]) {
                    if (!inCheese[nr][nc]) {
                        inCheese[nr][nc] = true;
                        cheese.add(new Position(nr, nc));
                    }
                } else {
                    outside[nr][nc] = true;
                    q.add(new Position(nr, nc));
                }
            }
        }
    }

    private static boolean isOut(int nr, int nc) {
        return nr < 0 || nr >= N || nc < 0 || nc >= M;
    }
}