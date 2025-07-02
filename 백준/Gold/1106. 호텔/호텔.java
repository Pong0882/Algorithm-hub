import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] dp = new int[1100 * 100 + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int people = Integer.parseInt(st.nextToken());

            for (int j = cost; j < dp.length; j++) {
                dp[j] = Math.max(dp[j], dp[j - cost] + people);
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] >= C) {
                result = i;
                break;
            }
        }

        System.out.println(result);
    }
}
