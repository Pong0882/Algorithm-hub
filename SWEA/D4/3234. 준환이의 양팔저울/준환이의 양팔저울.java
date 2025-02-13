import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.nio.Buffer;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    private static int N;
    private static int[] box;
    private static int[] permSortIdx; // 여따 perm 결과 저장해서 쓸거
    private static boolean[] select; // subset 하는데 이게 팀가르기고
    // true 일떄 하나씩 더하고 비교할꺼

    // perm 재료
    private static int depth;
    private static boolean[] visitedPerm;

    private static int result;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int Tcase = 1; Tcase <= T; Tcase++) {
            N = Integer.parseInt(br.readLine());

            result = 0;
            box = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                box[i] = Integer.parseInt(st.nextToken());
            }
            visitedPerm = new boolean[N];
            permSortIdx = new int[N];
            // select = new boolean[N];
            depth = N;

            perm(0);

            sb.append("#").append(Tcase).append(" ").append(result).append("\n");
        }
        System.out.print(sb);
    }

    private static void perm(int cnt) {
        if (cnt == depth) {
            // System.out.println(Arrays.toString(permSortIdx));
            // 이제 얘네로 subset 할것
            select = new boolean[N];
            subset(0, 0, 0);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (visitedPerm[i] == true)
                continue;
            visitedPerm[i] = true;
            permSortIdx[cnt] = box[i];
            perm(cnt + 1);
            visitedPerm[i] = false;
        }
    }

    private static void subset(int cnt, int teamT, int teamF) {
        if (teamT > teamF) {
            return;
        }
        if (cnt == N) {
            // System.out.println(Arrays.toString(select));
            // System.out.println(teamT + " " + teamF);
            result++;
            // System.out.println(result);
            return;
        }
        select[cnt] = true;
        subset(cnt + 1, teamT + permSortIdx[cnt], teamF);
        select[cnt] = false;
        subset(cnt + 1, teamT, teamF + permSortIdx[cnt]);
    }

    // private static void makereuslt() {
    // int teamT = 0;
    // int teamF = 0;
    // // int resultTmp = 0;
    // for (int i = 0; i < N; i++) {

    // if (select[i] == true) {
    // teamT += box[permSortIdx[i]];
    // } else {
    // teamF += box[permSortIdx[i]];
    // }
    // if (teamT > teamF)
    // return;
    // }
    // // System.out.println(teamT + " " + teamF);
    // result++;
    // }
}
