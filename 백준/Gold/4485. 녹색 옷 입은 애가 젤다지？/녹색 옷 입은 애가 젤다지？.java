import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] map;
    private static int[][] dijk;
    private static int[] dx = { 0, 0, -1, 1 };
    private static int[] dy = { -1, 1, 0, 0 };
    private static Queue<Node> arari;
    private static StringBuilder sb = new StringBuilder();

    private static class Node implements Comparable<Node> {
        int x, y, value;

        public Node(int x, int y, int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.value, o.value);
        }

    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int Tcase = 1;
        while (true) {
            N = Integer.parseInt(br.readLine());
            if (N == 0) {
                System.out.println(sb);
                return;
            }

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dijkstra();
            sb.append("Problem ").append(Tcase++).append(": ").append(dijk[N - 1][N - 1]).append("\n");
            // 입력값 확인인
            // for (int i = 0; i < N; i++) {
            // System.out.println(Arrays.toString(map[i]));
            // }
        }
    }

    private static void dijkstra() {
        dijk = new int[N][N];
        for (int[] row : dijk) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dijk[0][0] = map[0][0];

        arari = new PriorityQueue<>();
        arari.offer(new Node(0, 0, dijk[0][0]));

        while (!arari.isEmpty()) {
            Node curNode = arari.poll();
            int x = curNode.x;
            int y = curNode.y;
            int value = curNode.value;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                // check
                if (!check(nx, ny)) {
                    continue;
                }

                int newValue = value + map[nx][ny];
                if (newValue >= dijk[nx][ny]) {
                    continue;
                }
                dijk[nx][ny] = newValue;
                arari.offer(new Node(nx, ny, newValue));
            }

            // for (int i = 0; i < N; i++) {
            // System.out.println(Arrays.toString(dijk[i]));
            // }
            // System.out.println();
        }
    }

    private static boolean check(int x, int y) {
        return x < N && y < N && x >= 0 && y >= 0;
    }
}
