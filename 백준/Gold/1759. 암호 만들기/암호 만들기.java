import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, R;
    static List<Character> list = new ArrayList<>();
    static char[] select;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        select = new char[R];
        for (int i = 0; i < N; i++) {
            list.add(st.nextToken().charAt(0));
        }

        Collections.sort(list);
        // System.out.println(list);
        Combi(0, 0);
        System.out.println(sb);
    }

    private static void Combi(int cnt, int start) {
        if (cnt == R) {
            int vo = 0;
            int co = 0;
            for (int i = 0; i < R; i++) {
                char c = select[i];
                if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                    vo++;
                } else {
                    co++;
                }
            }
            if (vo >= 1 && co >= 2) {
                for (int i = 0; i < R; i++) {
                    sb.append(select[i]);
                }
                sb.append("\n");
            }
            return;
        }
        for (int i = start; i < N; i++) {
            select[cnt] = list.get(i);
            Combi(cnt + 1, i + 1);
        }
    }
}
