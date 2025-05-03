import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean[] paper;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        paper = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            if (Integer.parseInt(st.nextToken()) == 1) {
                paper[i] = true;
            }
        }

        int re = Integer.parseInt(br.readLine());

        while (re-- > 0) {
            st = new StringTokenizer(br.readLine());
            int gen = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            switch (gen) {
                case 1: // 남자
                    for (int i = num; i <= N; i += num) {
                        paper[i] = !paper[i];
                    }
                    break;

                case 2: // 여자
                    paper[num] = !paper[num];
                    int l = num - 1;
                    int r = num + 1;
                    while (l > 0 && r <= N) {
                        if (paper[l] == paper[r]) {
                            paper[l] = !paper[l--];
                            paper[r] = !paper[r++];
                        } else {
                            break;
                        }
                    }
                    break;
            }
            // for (int i = 1; i <= N; i++) {
            // if (paper[i]) {
            // sb.append(1).append(" ");
            // } else {
            // sb.append(0).append(" ");
            // }
            // }
            // System.out.println(sb);
            // sb = new StringBuilder();

        }

        for (int i = 1; i <= N; i++) {
            sb.append(paper[i] ? '1' : '0').append(" ");
            if (i % 20 == 0)
                sb.append('\n');

        }
        System.out.println(sb);
    }
}
