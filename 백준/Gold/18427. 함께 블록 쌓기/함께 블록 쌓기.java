import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M, H;
    static int[] dp;

    static final int MOD = 10_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // DP 층수로 만들고 한사람씩 늘려가면서 칸채우기

        N = Integer.parseInt(st.nextToken()); // 사람
        M = Integer.parseInt(st.nextToken()); // 한사람이 가질 수 있는 최대 블럭수
        H = Integer.parseInt(st.nextToken()); // 원하는 높이

        // M 은 저장용인가그냥.. 굳이? 어따쓰지

        dp = new int[H + 1];
        dp[0] = 1;
        // 이거 [0] 에 1 넣어놔야 계산이 잘돌아감

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            // 이거까지만 돌자그냥st.hasMoreTokens();
            int[] note = new int[H + 1];

            while (st.hasMoreTokens()) {
                int tmp = Integer.parseInt(st.nextToken());
                // 여기 dp 또 바로 갱신해버리면 반복계산되어버리는 문제 알지?
                // 이걸 어떻게 최적화시키는게 좋을까..
                // 다른곳 적어뒀다가하면 2*H 번 돌아버리는데..
                // 일단 풀어보자
                for (int h = tmp; h <= H; h++) {
                    note[h] = (note[h] + dp[h - tmp]) % MOD;
                }
            }
            for (int h = 0; h <= H; h++) {
                dp[h] = (dp[h] + note[h]) % MOD;
            }
            // System.out.println(Arrays.toString(dp));
        }
        System.out.println(dp[H]);
    }
}
