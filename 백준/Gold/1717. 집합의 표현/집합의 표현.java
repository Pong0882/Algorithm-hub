import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    static int n, m;
    static int[] boss;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        boss = new int[n + 1];
        for (int i = 0; i < n + 1; i++) {
            boss[i] = i;
        }
        while (--m >= 0) {
            st = new StringTokenizer(br.readLine());
            int what = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            switch (what) {
                case 0:
                    Union(a, b);
                    break;
                case 1:
                    sb.append(findboss(a) == findboss(b) ? "YES" : "NO").append("\n");
                    break;

                default:
                    break;
            }
        }
        System.out.println(sb);
    }

    private static void Union(int x, int y) {
        int a = findboss(x);
        int b = findboss(y);

        boss[a] = b;
    }

    private static int findboss(int x) {
        if (x == boss[x]) {
            return x;
        }
        return boss[x] = findboss(boss[x]);
    }
}