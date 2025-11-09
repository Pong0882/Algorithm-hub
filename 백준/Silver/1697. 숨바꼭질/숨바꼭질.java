import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[100_001];

        ArrayDeque<Integer[]> q = new ArrayDeque<>();

        int result = 0;

        q.add(new Integer[] { N, 0 });
        visited[N] = true;
        // 0 : 위치 / 1 : 시간
        while (!q.isEmpty()) {
            Integer[] cur = q.poll();

            if (cur[0] == K) {
                result = cur[1];
                break;
            }

            int[] next = { cur[0] - 1, cur[0] + 1, cur[0] * 2 };

            for (int nx : next) {
                if (nx >= 0 && nx <= 100_000 && !visited[nx]) {
                    visited[nx] = true;
                    q.add(new Integer[] { nx, cur[1] + 1 });
                }
            }
        }
        System.out.println(result);
    }
}
