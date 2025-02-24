
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int A, B, C;
    static int a, b, c, d, e, f;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int result = 0;
        int[] time = new int[100];

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            for (int j = a; j < b; j++) {
                time[j]++;
            }
        }
        // System.out.println(Arrays.toString(time));
        for (int i = 0; i < 100; i++) {
            if (time[i] == 1) {
                result += A;
            }
            if (time[i] == 2) {
                result += B * 2;
            }
            if (time[i] == 3) {
                result += C * 3;
            }
        }
        System.out.println(result);
    }
}
