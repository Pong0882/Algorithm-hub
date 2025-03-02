

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        // 정렬해서 투포인터?
        // 아니면 그냥 완탐?
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] paper = new int[N];
        for (int i = 0; i < N; i++) {
            paper[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(paper);
        int result = 0;
        // System.out.println(Arrays.toString(paper));
        for (int i = 0; i < N; i++) {
            for (int j = N - 1; j > i; j--) {
                int tmp = paper[i] + paper[j];
                // System.out.println(tmp);
                if (tmp == M) {
                    result++;
                } else if (tmp < M) {
                    i++;
                    j++;
                } else {
                    continue;
                }
            }
        }
        System.out.println(result);
    }
}
