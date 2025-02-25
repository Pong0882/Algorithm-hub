
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main{
    static int N;
    static int[][] paper;
    static int[] select;
    static boolean[] visited;
    static int result;
    static int ccc;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws NumberFormatException, IOException {
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        paper = new int[N][10];
        select = new int[9];
        visited = new boolean[9];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        perm(0);
        // System.out.println(ccc);
        System.out.print(result);
    }

    private static void perm(int cnt) throws IOException {
        if (cnt == 9) {
            // System.out.println(Arrays.toString(select));
            // ccc++;
            gogo();
            return;
        }
        for (int i = 1; i < 9; i++) {
            if (visited[i]) {
                continue;
            }
            if (cnt == 3) {
                select[cnt] = 0;
                perm(cnt + 1);
                return;
            }
            select[cnt] = i;
            visited[i] = true;
            perm(cnt + 1);
            visited[i] = false;
        }
    }

    private static void gogo() throws IOException {
        int tmp = 0;
        int j = 0;
        aa: for (int i = 0; i < N; i++) { // 이닝
            // System.out.println("--------------------------------------------");
            int flag = 0;
            int out = 0;
            while (true) {
                if (j == 9) {
                    j = 0;
                }
                int hit = paper[i][select[j]];
                if (hit == 0) {
                    out++;
                    if (out == 3) {
                        j++;
                        continue aa;
                    }
                } else {
                    flag = flag << hit;
                    flag += 1 << hit - 1;

                    for (int k = 3; k < 7; k++) {
                        if ((flag & 1 << k) != 0) {
                            tmp++;
                        }
                    }
                    flag &= 0b111;
                    // System.out.println(Integer.toBinaryString(flag) + " " + tmp + " " + out);

                    // String s = br.readLine();

                }
                j++;
            }
        }
        result = Math.max(result, tmp);
    }

}
