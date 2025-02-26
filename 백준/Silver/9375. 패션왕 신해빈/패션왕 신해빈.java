import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static StringBuilder sb = new StringBuilder();

    static int N, T;
    static Map<String, Integer> map;
    static int[] count;
    static boolean[] select;
    static int result;
    static int size;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        T = Integer.parseInt(br.readLine());
        for (int TC = 0; TC < T; TC++) {
            result = 1;
            N = Integer.parseInt(br.readLine());
            map = new HashMap<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String where = st.nextToken();

                // map.putIfAbsent(where, );
                map.compute(where, (k, v) -> (v == null) ? 1 : v + 1);
            }

            for (String string : map.keySet()) {
                result *= map.get(string) + 1;
            }

            // for (String string : map.keySet()) {
            // System.out.println(string);
            // }
            sb.append(result - 1).append("\n");
        }
        System.out.println(sb);
    }
}
