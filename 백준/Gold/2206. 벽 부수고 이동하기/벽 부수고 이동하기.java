import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M;
    private static int[][] paper;
    private static int[][][] visited;
    private static int[] dx = { 0, 0, 1, -1 };
    private static int[] dy = { -1, 1, 0, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paper = new int[N][M];
        visited = new int[N][M][2];

        for (int i = 0; i < N; i++) {
            char[] a = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                paper[i][j] = a[j] - '0';
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        Queue<int[]> que = new ArrayDeque<>();
        que.offer(new int[] { 0, 0, 1, 1 }); // x, y, 거리, 목숨
        visited[0][0][1] = 1;

        while (!que.isEmpty()) {
            int[] now = que.poll();
            int x = now[0];
            int y = now[1];
            int cnt = now[2];
            int life = now[3]; // 현재 목숨

            if (x == N - 1 && y == M - 1) {
                return cnt; // 도착하면 거리 반환
            }

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!check(nx, ny))
                    continue;

                if (paper[nx][ny] == 0 && visited[nx][ny][life] == 0) {
                    visited[nx][ny][life] = 1;
                    que.offer(new int[] { nx, ny, cnt + 1, life });
                }

                else if (paper[nx][ny] == 1 && life == 1 && visited[nx][ny][1] == 0) {
                    visited[nx][ny][1] = 1;
                    que.offer(new int[] { nx, ny, cnt + 1, 0 });
                }
            }
        }
        return -1;
    }

    private static boolean check(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}