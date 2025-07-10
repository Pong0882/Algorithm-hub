import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, S;
    static int[] paper;
    static int result = Integer.MAX_VALUE;
    static int sum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());

        paper = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            paper[i] = Integer.parseInt(st.nextToken());
        }

        int s = 0;
        int e = 0;

        sum = paper[0];

        while (s < N) {
            if (sum >= S) {
                result = Math.min(result, e - s + 1);
                sum -= paper[s++];
            } else {
                if (e + 1 < N) {
                    sum += paper[++e];
                } else {
                    break;
                }
            }
        }
        System.out.println(result == Integer.MAX_VALUE ? 0 : result);
    }
}
