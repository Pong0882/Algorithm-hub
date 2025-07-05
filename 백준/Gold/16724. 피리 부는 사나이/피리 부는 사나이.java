import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int N, M;

    static int[][] map;
    static int[][] paper;
    static int result;

    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        paper = new int[N][M];

        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();

            for (int j = 0; j < M; j++) {
                switch (tmp[j]) {
                    case 'U':
                        map[i][j] = 0;
                        break;
                    case 'R':
                        map[i][j] = 1;
                        break;
                    case 'D':
                        map[i][j] = 2;
                        break;
                    case 'L':
                        map[i][j] = 3;
                        break;
                }
            }
        }

        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (paper[i][j] == 0) {
                    DFS(i, j, ++cnt);
                }
            }
        }
        System.out.println(result);
    }

    private static void DFS(int i, int j, int cnt) {
        ArrayDeque<Integer[]> que = new ArrayDeque<>();

        que.add(new Integer[] { i, j });
        paper[i][j] = cnt;

        while (!que.isEmpty()) {
            Integer[] tmp = que.poll();
            int r = tmp[0];
            int c = tmp[1];

            int nr = r + dr[map[r][c]];
            int nc = c + dc[map[r][c]];

            if (paper[nr][nc] == cnt) {
                result++;
            }
            if (paper[nr][nc] == 0) {
                que.add(new Integer[] { nr, nc });
                paper[nr][nc] = cnt;
            }
        }
    }
}
