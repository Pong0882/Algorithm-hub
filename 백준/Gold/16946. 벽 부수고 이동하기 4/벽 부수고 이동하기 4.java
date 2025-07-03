import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static boolean[][] map;
    static int[][] paper;
    static int[][] result;
    static int[] dr = { 0, 0, 1, -1 };
    static int[] dc = { 1, -1, 0, 0 };
    static Map<Integer, Integer> groupSize = new HashMap<>();
    static int groupNum = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        paper = new int[N][M];
        result = new int[N][M];

        for (int i = 0; i < N; i++) {
            char[] tmp = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (tmp[j] == '1') {
                    map[i][j] = true;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!map[i][j] && paper[i][j] == 0) {
                    int size = BFS(i, j);
                    groupSize.put(groupNum, size);
                    groupNum++;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j]) {
                    sb.append(getWallValue(i, j));
                } else {
                    sb.append(0);
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    private static int BFS(int i, int j) {
        int count = 1;
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[] { i, j });
        paper[i][j] = groupNum;

        while (!q.isEmpty()) {
            int[] tmp = q.poll();
            int r = tmp[0];
            int c = tmp[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (!check(nr, nc))
                    continue;
                if (map[nr][nc])
                    continue;
                if (paper[nr][nc] != 0)
                    continue;
                q.add(new int[] { nr, nc });
                paper[nr][nc] = groupNum;
                count++;
            }
        }
        return count;
    }

    private static int getWallValue(int i, int j) {
        Set<Integer> adjacent = new HashSet<>();
        int sum = 1;
        for (int d = 0; d < 4; d++) {
            int nr = i + dr[d];
            int nc = j + dc[d];
            if (!check(nr, nc))
                continue;
            int gNum = paper[nr][nc];
            if (gNum > 0 && !adjacent.contains(gNum)) {
                sum += groupSize.get(gNum);
                adjacent.add(gNum);
            }
        }
        return sum % 10;
    }

    private static boolean check(int nr, int nc) {
        return nr >= 0 && nc >= 0 && nr < N && nc < M;
    }
}
