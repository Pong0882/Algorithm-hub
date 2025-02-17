import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static int paper[][];
    static boolean visited[][];
    static int result;
    static LinkedList<Integer> cheezeCheck;
    static LinkedList<Integer[]> meltCheeze = new LinkedList<>();
    static int[] dr = { 1, -1, 0, 0 };
    static int[] dc = { 0, 0, 1, -1 };
    static int cheeze;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        paper = new int[R][C];
        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
                if (paper[i][j] == 1) {
                    cheeze++;
                }
            }
        }
        // System.out.println(cheeze);
        Queue<Integer[]> queue = new ArrayDeque<>();
        while (cheeze > 0) {
            if (queue.isEmpty()) {
                queue.add(new Integer[] { 0, 0 });
            }
            visited = new boolean[R][C];
            // cheezeCheck = new LinkedList<>();

            while (!queue.isEmpty()) {
                Integer[] where = queue.poll();
                int r = where[0];
                int c = where[1];
                for (int i = 0; i < 4; i++) {
                    int nr = r + dr[i];
                    int nc = c + dc[i];
                    if (!check(nr, nc))
                        continue;
                    if (visited[nr][nc] == false) {
                        if (paper[nr][nc] == 0) {
                            visited[nr][nc] = true;
                            queue.add(new Integer[] { nr, nc });
                        } else {
                            visited[nr][nc] = true;
                        }
                    } else if (paper[nr][nc] == 1 && visited[nr][nc] == true) {
                        meltCheeze.add(new Integer[] { nr, nc });
                    }
                }
            }
            while (!meltCheeze.isEmpty()) {
                Integer[] where = meltCheeze.poll();
                int r = where[0];
                int c = where[1];
                if (paper[r][c] == 0) {
                    continue;
                }
                paper[r][c] = 0;
                cheeze--;
            }
            result++;
            // for (int i = 0; i < R; i++) {
            // System.out.println(Arrays.toString(paper[i]));
            // }
            // System.out.println();
        }
        System.out.println(result);
    }

    private static boolean check(int nr, int nc) {
        // TODO Auto-generated method stub
        return nr >= 0 && nc >= 0 && nr < R && nc < C;
    }
}