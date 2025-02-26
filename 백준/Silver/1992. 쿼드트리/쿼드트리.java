import java.io.*;
import java.util.*;

public class Main {
    public static int[][] data;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        // sb.append(i).append(' ');

        int n = Integer.parseInt(br.readLine()); // 숫자하나 받기
        data = new int[n][n];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                data[i][j] = s.charAt(j) - '0';
            }
        }
        ////////////////////////////////
        quadTree(n, 0, 0);
        System.out.println(sb);
    };

    public static void quadTree(int n, int a, int b) {
        int tmp = data[a][b];
        int half = n / 2;
        if (n == 1) {
            sb.append(tmp);
            return;
        }
        for (int i = a; i < a + n; i++) {
            for (int j = b; j < b + n; j++) {
                if (tmp != data[i][j]) {
                    sb.append('(');
                    quadTree(half, a, b);
                    quadTree(half, a, b + half);
                    quadTree(half, a + half, b);
                    quadTree(half, a + half, b + half);
                    sb.append(')');
                    return;
                }
            }
        }
        sb.append(tmp);
        return;
    }
}
