import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] c = br.readLine().toCharArray();

        int[] cnt = new int[2];
        for (char ch : c) {
            cnt[ch - 48]++;
        }
        // System.out.println(cnt[0] + " " + cnt[1]);

        cnt[0] /= 2;
        int maxOne = cnt[1] / 2;
        StringBuilder sb = new StringBuilder();
        for (char ch : c) {
            int num = ch - 48;
            if (num == 0) {
                if (cnt[0]-- > 0) {
                    sb.append(num);
                }
            } else {
                if (cnt[1]-- <= maxOne) {
                    sb.append(num);
                }
            }
        }
        System.out.println(sb);
    }
}
