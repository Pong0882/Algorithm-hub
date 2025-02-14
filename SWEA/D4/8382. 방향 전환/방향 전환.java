import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    private static final int[] dx = { -1, 0, 1, 0 };
    private static final int[] dy = { 0, 1, 0, -1 };
    private static int[][][] visited;
    private static int x1, y1, x2, y2;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int Tcase = 1; Tcase <= T; Tcase++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken()) + 100;
            y1 = Integer.parseInt(st.nextToken()) + 100;
            x2 = Integer.parseInt(st.nextToken()) + 100;
            y2 = Integer.parseInt(st.nextToken()) + 100;
            visited = new int[201][201][2];

            System.out.println("#" + Tcase + " " + bfs());
        }
    }

    private static int bfs() {
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] { x1, y1, 0, 0 }); // 가로 이동 시작
        que.offer(new int[] { x1, y1, 0, 1 }); // 세로 이동 시작
        visited[x1][y1][0] = 1;
        visited[x1][y1][1] = 1;

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int x = cur[0], y = cur[1], cnt = cur[2], dir = cur[3];

            if (x == x2 && y == y2)
                return cnt;

            for (int d = 1; d < 4; d += 2) {
                int s = (dir + d) % 4;
                int u = (dir + d) % 2;
                int nx = x + dx[s];
                int ny = y + dy[s];

                if (!check(nx, ny))
                    continue;
                if (visited[nx][ny][u] == 0) {
                    visited[nx][ny][u] = 1;
                    que.offer(new int[] { nx, ny, cnt + 1, u });
                }
            }
        }
        return -1;
    }

    private static boolean check(int x, int y) {
        return x >= 0 && x <= 200 && y >= 0 && y <= 200;
    }
}
