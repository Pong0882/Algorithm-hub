import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, d, K, c;
    static int[] paper;
    static int[] check;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        paper = new int[N];
        check = new int[d + 1];

        for (int i = 0; i < N; i++) {
            paper[i] = Integer.parseInt(br.readLine());
        }
        int cnt = 0;

        // init
        for (int i = 0; i < K; i++) {
            if (check[paper[i]] == 0)
                cnt++;
            check[paper[i]]++;
        }

        result = (check[c] == 0) ? cnt + 1 : cnt;

        for (int i = 1; i < N; i++) {
            int left = paper[(i - 1) % N];
            int right = paper[(i + K - 1) % N];

            if (--check[left] == 0)
                cnt--;

            if (check[right]++ == 0) {
                cnt++;
                result = Math.max(result, check[c] == 0 ? cnt + 1 : cnt);
            }
        }

        System.out.println(result);
    }
}
