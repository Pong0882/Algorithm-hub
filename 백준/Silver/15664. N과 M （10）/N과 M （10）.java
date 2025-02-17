import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] card;
    // static boolean[] visited;
    static int[] select;
    static int depth;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        card = new int[N];
        for (int i = 0; i < N; i++) {
            card[i] = Integer.parseInt(st.nextToken());
        }
        depth = M;
        select = new int[M];
        // visited = new boolean[N];
        Arrays.sort(card);

        combi(0, 0);
        System.out.println(sb);
    }

    private static void combi(int cnt, int start) {
        if (cnt == depth) {
            for (int i = 0; i < M; i++) {
                sb.append(select[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        int lastNum = 0;
        for (int i = start; i < N; i++) {
            if (lastNum == card[i]) {
                continue;
            }
            // visited[i] = true;
            select[cnt] = card[i];
            combi(cnt + 1, i + 1);
            // visited[i] = false;
            lastNum = card[i];
        }
    }
}
