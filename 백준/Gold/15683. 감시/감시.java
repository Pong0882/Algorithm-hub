import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M; // Static 변수는 Method Area 에 저장 근데 new 로 객체생성하면 객체는 Heap 에 생성
    // Static 변수는 클래스가 로딩되어있는 동안 계속 참조 (GC 대상 X), Static 참조가 살아있어서 GC 가 못치운다!

    // CCTV의 수는 8개 이하, 종류는 5가지, 방향은 4방, N과 M 8 이하, -> 완탐때려도 안터질듯

    static int[][] map;

    // CCTV 기억해두자
    static class CCTV {
        int r, c, type;

        // Generate Constructors
        public CCTV(int r, int c, int type) {
            this.r = r;
            this.c = c;
            this.type = type;
        }
    }

    static List<CCTV> cctvs = new ArrayList<>();

    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    // 12시 부터 시계방향, CCTV 타입 별 방향
    static int[][][] directions = {
            {}, // 0번은 사용 안함
            { { 0 }, { 1 }, { 2 }, { 3 } }, // 1번
            { { 0, 2 }, { 1, 3 } }, // 2번: 반대
            { { 0, 1 }, { 1, 2 }, { 2, 3 }, { 3, 0 } }, // 3번: 직각
            { { 0, 1, 2 }, { 1, 2, 3 }, { 2, 3, 0 }, { 3, 0, 1 } }, // 4번: 세 방향
            { { 0, 1, 2, 3 } } // 5번
    };

    static int result = 100_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                // 6은 벽, 1~5는 CCTV 종류

                map[i][j] = tmp;
                if (tmp > 0 && tmp < 6)
                    cctvs.add(new CCTV(i, j, tmp));
            }
        }

        dfs(0);
        System.out.println(result);
    }

    private static void dfs(int depth) {
        // Base Condition
        if (depth == cctvs.size()) {
            check();
            return;
        }

        CCTV cctv = cctvs.get(depth);
        int[][] directionSet = directions[cctv.type];

        // 맵이 작아서 바라보는곳 +7했다가 백트래킹하면서 -7 해줄게요 그래서 0 이 아니면 감시되는것
        for (int[] dirs : directionSet) {
            // 감시 표시
            for (int dir : dirs) {
                mark(cctv.r, cctv.c, dir, 7); // +7
            }

            dfs(depth + 1);

            // 백트래킹
            for (int dir : dirs) {
                mark(cctv.r, cctv.c, dir, -7); // -7
            }
        }
    }

    private static void mark(int r, int c, int dir, int delta) {
        int nr = r + dr[dir];
        int nc = c + dc[dir];

        while (nr >= 0 && nr < N && nc >= 0 && nc < M) {
            if (map[nr][nc] == 6)
                break; // 벽
            if (map[nr][nc] != 6 && (map[nr][nc] < 1 || map[nr][nc] > 5)) {
                map[nr][nc] += delta; // CCTV가 아닌 곳만 표시
            }
            nr += dr[dir];
            nc += dc[dir];
        }
    }

    private static void check() {
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    count++;
                }
            }
        }
        result = Math.min(result, count);
    }
}
