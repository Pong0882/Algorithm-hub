import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] paper;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        paper = new int[N];
        int tallNum = 1;
        while (N-- > 0) {
            int leftNum = Integer.parseInt(st.nextToken());

            int index = 0;
            while (true) {
                if (paper[index] != 0) {
                    index++;
                } else {
                    break;
                }
            }
            while (leftNum > 0) {
                index++;
                if (paper[index] != 0) {
                    continue;
                }
                leftNum--;
            }
            paper[index] = tallNum++;
        }

        // 출력
        for (int i = 0; i < paper.length; i++) {
            sb.append(paper[i]).append(" ");
        }
        System.out.println(sb);
    }
}
