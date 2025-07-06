import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, B, C;
    static int[] paper;
    static long result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        paper = new int[N + 2];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            paper[i] = Integer.parseInt(st.nextToken());
        }

        if (C >= B) {
            for (int i = 0; i < N; i++) {
                buy(i, 1, paper[i]);
            }
        } else {
            for (int i = 0; i < N; i++) {
                if (paper[i + 1] > paper[i + 2]) {
                    int min = Math.min(paper[i], paper[i + 1] - paper[i + 2]);
                    if (min > 0) {
                        buy(i, 2, min);
                    }
                }

                int min = Math.min(paper[i], Math.min(paper[i + 1], paper[i + 2]));
                if (min > 0) {
                    buy(i, 3, min);
                }

                if (paper[i] > 0) {
                    buy(i, 1, paper[i]);
                }
            }
        }

        System.out.println(result);
    }

    private static void buy(int i, int n, int cnt) {
        switch (n) {
            case 1:
                result += 1L * B * cnt;
                paper[i] -= cnt;
                break;
            case 2:
                result += 1L * (B + C) * cnt;
                paper[i] -= cnt;
                paper[i + 1] -= cnt;
                break;
            case 3:
                result += 1L * (B + 2 * C) * cnt;
                paper[i] -= cnt;
                paper[i + 1] -= cnt;
                paper[i + 2] -= cnt;
                break;
        }
    }

}
