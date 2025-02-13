import java.io.*;
import java.util.*;

public class Main {
    private static int[][] data;
    private static int N, M;
    private static Queue<int[]> queue = new LinkedList<>();
    private static int dirX[] = { 1, 0, -1, 0 };
    private static int dirY[] = { 0, -1, 0, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        data = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                data[i][j] = Integer.parseInt(st.nextToken());
                if (data[i][j] == 1) {
                    queue.add(new int[] { i, j });
                }

            }
        }

        int result = 0;
        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0];
            int y = now[1];

            for (int i = 0; i < 4; i++) {
                int tmpX = x + dirX[i];
                int tmpY = y + dirY[i];

                if (tmpX >= 0 && tmpY >= 0 && tmpX < N && tmpY < M && data[tmpX][tmpY] == 0) {
                    data[tmpX][tmpY] = data[x][y] + 1;
                    queue.add(new int[] { tmpX, tmpY });
                }
            }
            result = data[x][y];
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (data[i][j] == 0) {
                    result = 0;
                }
            }
        }

        System.out.println(result - 1);
    };
}
