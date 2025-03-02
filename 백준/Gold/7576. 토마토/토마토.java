

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    // 이거 비지티드 하나로도 쓸수 있겠는데?

    static boolean[][] visited;

    static ArrayDeque<Integer> q1 = new ArrayDeque<>();
    static ArrayDeque<Integer> q2 = new ArrayDeque<>();
    static int[] dr = { 1, -1, 0, 0 };
    static int[] dc = { 0, 0, 1, -1 };
    static int cnt = 0;
    static int result = -1;
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if (tmp == 0) {
                    cnt++; // cnt 가 0이 아니면 -1 출력할것
                } else if (tmp == -1) {
                    visited[i][j] = true;
                } else {
                    q1.offer(i * C + j);
                    visited[i][j] = true;
                }
            }
        }
        // for (int i = 0; i < R; i++) {
        // System.out.println(Arrays.toString(visited[i]));
        // }
        while (!q1.isEmpty() || !q2.isEmpty()) {
            result++;
            if (!q1.isEmpty()) {
                while (!q1.isEmpty()) {
                    int tmp = q1.poll();
                    int r = tmp / C;
                    int c = tmp % C;
                    for (int i = 0; i < 4; i++) {
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        if (!check(nr, nc) || visited[nr][nc])
                            continue;
                        q2.offer(nr * C + nc);
                        visited[nr][nc] = true;
                        cnt--;
                    }
                }
            } else {
                while (!q2.isEmpty()) {
                    int tmp = q2.poll();
                    int r = tmp / C;
                    int c = tmp % C;
                    for (int i = 0; i < 4; i++) {
                        int nr = r + dr[i];
                        int nc = c + dc[i];
                        if (!check(nr, nc) || visited[nr][nc])
                            continue;
                        q1.offer(nr * C + nc);
                        visited[nr][nc] = true;
                        cnt--;
                    }
                }
            }
        }

        System.out.println(cnt == 0 ? result : -1);
    }

    private static boolean check(int nr, int nc) {
        return nr >= 0 && nc >= 0 && nr < R && nc < C;
    }
}
