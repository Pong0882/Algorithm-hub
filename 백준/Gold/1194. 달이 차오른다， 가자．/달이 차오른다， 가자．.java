import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static int[][] paper;
    static boolean[][][] visited;
    static int result;
    // static int key;
    static ArrayDeque<Integer[]> q = new ArrayDeque<>();
    // static boolean[] getKey;
    static int[] dr = { 1, -1, 0, 0 };
    static int[] dc = { 0, 0, 1, -1 };
    static HashMap<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        paper = new int[R][C];
        visited = new boolean[64][R][C];
        map.put((int) 'a', 0);
        map.put((int) 'b', 1);
        map.put((int) 'c', 2);
        map.put((int) 'd', 3);
        map.put((int) 'e', 4);
        map.put((int) 'f', 5);
        map.put((int) 'A', 10);
        map.put((int) 'B', 11);
        map.put((int) 'C', 12);
        map.put((int) 'D', 13);
        map.put((int) 'E', 14);
        map.put((int) 'F', 15);
        for (int i = 0; i < R; i++) {
            char[] c = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                switch (c[j]) {
                    case '0':
                        q.add(new Integer[] { i, j, 0, 0 }); // R, C, 시간, key
                        visited[0][i][j] = true;
                        break;
                    case '1':
                        paper[i][j] = -1;
                        break;
                    case '#':
                        paper[i][j] = -2;
                        break;
                    case '.':
                        paper[i][j] = 0;
                        break;

                    default:
                        paper[i][j] = c[j];
                }
            }
        }
        // for (int i = 0; i < R; i++) {
        // System.out.println(Arrays.toString(paper[i]));
        // }
        // getKey = new boolean[6];
        while (!q.isEmpty()) {
            Integer[] cur = q.poll();
            int r = cur[0];
            int c = cur[1];
            int time = cur[2];
            int key = cur[3];
            // br.readLine();

            // System.out.println(key + " (" + r + ", " + c + ") " + time);
            if (paper[r][c] == -1) { // 탈출~
                System.out.println(time);
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (!outCheck(nr, nc) || visited[key][nr][nc] || paper[nr][nc] == -2) { // 못가
                    continue;
                }
                if (paper[nr][nc] != 0 && paper[nr][nc] != -1) {
                    int keyIndex = map.get(paper[nr][nc]);
                    // System.out.println(keyIndex);
                    if (keyIndex > 9) { // 문 발견!!!
                        int keyName = 1 << (keyIndex - 10); // 어떤 문이니~~???
                        if ((key & keyName) == 0) { // 열쇠 없다~
                            continue;
                        } else { // 열쇠 있다!!!
                            q.add(new Integer[] { nr, nc, time + 1, key });
                            visited[key][nr][nc] = true;
                        }
                    } else { // 열쇠 발견!
                        int keyName = 1 << keyIndex;
                        int newKey = key;
                        if ((newKey & keyName) == 0) {
                            newKey |= keyName; 
                        }
                        q.add(new Integer[] { nr, nc, time + 1, newKey });
                        visited[newKey][nr][nc] = true;
                    }
                } else {
                    q.add(new Integer[] { nr, nc, time + 1, key });
                    visited[key][nr][nc] = true;
                }
            }
        }
        System.out.println(-1);
    }

    private static boolean outCheck(int r, int c) {
        return r >= 0 && c >= 0 && r < R && c < C;
    }
}
