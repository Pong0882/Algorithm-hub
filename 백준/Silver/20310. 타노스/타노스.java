import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        char[] c = br.readLine().toCharArray();
        ArrayDeque<Integer> dq = new ArrayDeque<>();

        boolean[] flag = new boolean[2];
        flag[1] = true;
        int one = 0;
        for (char ch : c) {
            int n = ch - '0';
            flag[n] = !flag[n];
            if (flag[n]) {
                if (n == 1) {
                    one++;
                } else {
                    sb.append(n);
                }
            }
        }
        for (int i = 0; i < one; i++) {
            sb.append(1);
        }
        System.out.println(sb);
    }
}
