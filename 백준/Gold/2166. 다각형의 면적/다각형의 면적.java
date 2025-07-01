import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] x = new long[N];
        long[] y = new long[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Long.parseLong(st.nextToken());
            y[i] = Long.parseLong(st.nextToken());
        }

        long sum1 = 0;
        long sum2 = 0;

        for (int i = 0; i < N; i++) {
            int next = (i + 1) % N;
            sum1 += x[i] * y[next];
            sum2 += y[i] * x[next];
        }

        double area = Math.abs(sum1 - sum2) / 2.0;

        System.out.printf("%.1f\n", area);
    }
}
