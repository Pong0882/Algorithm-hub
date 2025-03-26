import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] boss;
    static int result;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        boss = new int[N];
        for (int i = 0; i < N; i++) {
            boss[i] = i;
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int conn = Integer.parseInt(st.nextToken());
                if (conn == 1) {
                    Union(i, j);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int tmp = Integer.parseInt(st.nextToken());
        result = bossFind(tmp-1);
        for (int i = 0; i < M-1; i++) {
            tmp = Integer.parseInt(st.nextToken());
            if(result != bossFind(tmp-1)){
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }

    private static void Union(int x, int y) {
        int a = bossFind(x);
        int b = bossFind(y);

        boss[a] = b;
    }

    private static int bossFind(int x) {;
        if(x == boss[x]){
            return x;
        }
        return boss[x] = bossFind(boss[x]);
    }
}
