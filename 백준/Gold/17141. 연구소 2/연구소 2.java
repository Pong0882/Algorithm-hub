
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main{
    static int N, M;
    static int[] dr = { 0, 0, 1, -1 };
    static int[] dc = { 1, -1, 0, 0 };
    static int[][] paper;
    static boolean[][] visited;
    static int result = Integer.MAX_VALUE;
    static ArrayList<Integer> virus = new ArrayList<>();
    static int[] select;
    static int posi;
    static int place;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        paper = new int[N][N];
        select = new int[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
                if (paper[i][j] == 0) {
                    place++;
                }
                if (paper[i][j] == 2) {
                    place++;
                    virus.add(i * N + j);
                    posi++;
                    paper[i][j] = 0;
                }
            }
        }
        // combi
        combi(0, 0);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result - 1);
    }

    private static void combi(int cnt, int start) {
        if (cnt == M) {
            cul();
            // System.out.println(Arrays.toString(select));
            return;
        }

        for (int i = start; i < posi; i++) {
            select[cnt] = virus.get(i);
            combi(cnt + 1, i + 1);
        }
    }

    private static void cul() {
        ArrayDeque<Integer[]> q = new ArrayDeque<>();
        int checkok = place;
        visited = new boolean[N][N];
        for (int i = 0; i < M; i++) {
            int r = select[i] / N;
            int c = select[i] % N;
            q.add(new Integer[] { r, c });
            visited[r][c] = true;
        }
        int time = 0;
        while (!q.isEmpty()) {
            int re = q.size();
            time++;
            for (int z = 0; z < re; z++) {
                Integer[] tmp = q.poll();
                checkok--;
                int r = tmp[0];
                int c = tmp[1];
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (!check(nr, nc)) {
                        continue;
                    }
                    if (visited[nr][nc] == true || paper[nr][nc] == 1) {
                        continue;
                    }
                    q.add(new Integer[] { nr, nc });
                    visited[nr][nc] = true;
                }
            }
        }
        if (checkok == 0) {
            if (result > time) {
                result = time;
            }
        }
        return;
    }

    private static boolean check(int nr, int nc) {
        return nr >= 0 && nc >= 0 && nr < N && nc < N;
    }
}
