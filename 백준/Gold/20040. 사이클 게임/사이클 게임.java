import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] boss;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        boss = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            boss[i] = i;
        }

        int result = 0;

        for (int i = 1; i < M + 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (!union(a, b)) {
                result = i;
                break;
            }
        }

        System.out.println(result);
    }

    private static boolean union(int a, int b) {
        int bossA = find(a);
        int bossB = find(b);

        if (bossA == bossB)
            return false;

        boss[bossB] = bossA;
        return true;
    }

    private static int find(int x) {
        if (boss[x] == x)
            return x;

        return boss[x] = find(boss[x]);
    }
}
