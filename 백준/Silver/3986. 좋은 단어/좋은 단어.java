import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int result = 0;
        for (int i = 0; i < n; i++) {
            char[] c = br.readLine().toCharArray();
            ArrayDeque<Character> que = new ArrayDeque<>();

            for (int j = 0; j < c.length; j++) {
                char tmp = c[j];
                if (que.isEmpty()) {
                    que.addFirst(tmp);
                } else if (que.peekFirst().equals(tmp)) {
                    que.pollFirst();
                } else {
                    que.addFirst(tmp);

                }
            }
            if (!que.isEmpty()) {
                continue;
            }
            result++;
            // System.out.println(result);
        }
        System.out.println(result);
    }
}
