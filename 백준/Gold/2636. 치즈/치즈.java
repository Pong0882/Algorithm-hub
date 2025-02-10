import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();

    private static int A, B, N, M, result;
    private static int[][] paper;

    private static int bfscnt = 0;
    private static int cheesecnt = 0;
    private static int cheesetmp;

    private static int[] dx = { 1, -1, 0, 0 };
    private static int[] dy = { 0, 0, 1, -1 };

    public static void main(String[] args) throws IOException {
        // 생각의 흐름
        // BFS를 반복해서 돌리자 - cnt 로 돌아간 수를 세자
        // 0이나 1만 찾아서 들어간다.
        // 0은 -1로 바꾸고 1은 0으로 바꾼다. 1의 수를 기억한다 cheezecnt

        // 생각2
        // 치즈가 다 사라졌을때 반복을 끝낼 법을 생각해보니 전체 치즈수를 세서 빼자.
        // 그럼 cheezetmp로 마지막 치즈를 기억은 해야겠다.
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        paper = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
                if (paper[i][j] == 1) {
                    cheesecnt++;
                }
            }
        }

        // System.out.println(cheesecnt);
        bfs();
        System.out.println(bfscnt);
        System.out.println(cheesetmp);
    }

    private static void bfs() {

        Queue<Integer[]> queue = new ArrayDeque<>();
        Queue<Integer[]> cheeze = new ArrayDeque<>();
        queue.offer(new Integer[] { A, B });
        paper[A][B] = 4;

        while (cheesecnt > 0) {
            bfscnt++;
            cheesetmp = cheesecnt;
            while (!queue.isEmpty()) {
                Integer[] tmp = queue.poll();
                int x = tmp[0];
                int y = tmp[1];
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (!check(nx, ny)) {
                        continue;
                    }
                    if (paper[nx][ny] == 0) {
                        queue.offer(new Integer[] { nx, ny });
                        paper[nx][ny] = 4;
                    }
                    if (paper[nx][ny] == 1) {
                        // if (!cheeze.contains(new Integer[] { nx, ny }))
                        cheeze.offer(new Integer[] { nx, ny });
                        // cheesecnt--;
                    }
                }
            }
            while (!cheeze.isEmpty()) {
                Integer[] tmp = cheeze.poll();
                int cx = tmp[0];
                int cy = tmp[1];
                if (paper[cx][cy] == 1) {
                    cheesecnt--;
                    paper[cx][cy] = 0;
                }
                queue.offer(new Integer[] { cx, cy });
            }
            // System.out.println(cheesetmp);
            // for (int i = 0; i < N; i++) {
            // System.out.println(Arrays.toString(paper[i]));
            // }
        }
    }

    private static boolean check(int x, int y) {
        return (x >= 0 && y >= 0 && x < N && y < M);
    }
}