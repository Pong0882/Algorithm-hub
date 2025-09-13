import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String A;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        A = br.readLine();
        String B = br.readLine();

        DFS(B);
        System.out.println(result);
    }

    private static void DFS(String s) {
        if (result == 1) {
            return;
        }

        if (s.length() == A.length()) {
            if (s.equals(A))
                result = 1;
            return;
        }

        if (s.charAt(s.length() - 1) == 'A') {
            DFS(s.substring(0, s.length() - 1));
        }

        if (s.charAt(0) == 'B') {
            StringBuilder sb = new StringBuilder(s.substring(1));
            DFS(sb.reverse().toString());
        }
    }
}