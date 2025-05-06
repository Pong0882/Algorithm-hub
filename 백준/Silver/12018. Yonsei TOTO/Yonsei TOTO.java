import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] paper;
    static int[] tmp;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paper = new int[N];
        int index = 0;

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int size = Integer.parseInt(st.nextToken());
            int cut = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            tmp = new int[size];
            for (int i = 0; i < size; i++) {
                tmp[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(tmp);
            if (size >= cut) {
                paper[index++] = tmp[size - cut];
            } else {
                paper[index++] = 1;
            }
        }
        // System.out.println(Arrays.toString(paper));
        Arrays.sort(paper);
        index = 0;
        while (index < paper.length && M >= paper[index]) {
            M -= paper[index++];
            result++;
        }
        System.out.println(result);
    }
}
