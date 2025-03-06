
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean[][][] visited;
    static int[] d3 = { 9, 9, 3, 3, 1, 1 };
    static int[] d2 = { 3, 1, 1, 9, 3, 9 };
    static int[] d1 = { 1, 3, 9, 1, 9, 3 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        int[] SCV = new int[3];
        for (int i = 0; i < N; i++) {
            SCV[i] = Integer.parseInt(st.nextToken());
        }

        visited = new boolean[61][61][61];
        Queue<int[]> queue = new ArrayDeque<>();
        int result = 0;
        queue.add(new int[] { SCV[0], SCV[1], SCV[2], 0 });
        while (!queue.isEmpty()) {
            int[] tmp = queue.poll();
            int A = tmp[0];
            int B = tmp[1];
            int C = tmp[2];
            int cnt = tmp[3];
            // System.out.println(A + " " + B + " " + C + " " + cnt);
            if (A == 0 && B == 0 && C == 0) {
                result = cnt;
                break;
            }
            for (int i = 0; i < 6; i++) {
                int na = Math.max(0, A - d3[i]);
                int nb = Math.max(0, B - d2[i]);
                int nc = Math.max(0, C - d1[i]);

                if (visited[na][nb][nc] == true) {
                    continue;
                }
                visited[na][nb][nc] = true;
                queue.add(new int[] { na, nb, nc, cnt + 1 });
            }
        }
        System.out.println(result);
    }

}
