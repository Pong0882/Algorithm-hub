import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] dp;
    static int[][] paper;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        paper = new int[N][3];
        result = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            paper[i][0] = Integer.parseInt(st.nextToken());
            paper[i][1] = Integer.parseInt(st.nextToken());
            paper[i][2] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < 3; i++) {
            dp = new int[3];
            Arrays.fill(dp, Integer.MAX_VALUE / 100);
            dp[i] = paper[0][i];

            for (int j = 1; j < N; j++) {
                int tmpR = paper[j][0] + Math.min(dp[1], dp[2]);
                int tmpG = paper[j][1] + Math.min(dp[0], dp[2]);
                int tmpB = paper[j][2] + Math.min(dp[1], dp[0]);

                dp[0] = tmpR;
                dp[1] = tmpG;
                dp[2] = tmpB;
            }

            for (int j = 0; j < 3; j++) {
                if (j == i)
                    continue;
                result = Math.min(dp[j], result);
            }
        }

        System.out.println(result);
    }
}
