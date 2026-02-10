import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static boolean[] start1, start2, end;
    static final int MAX = 10_000_000;
    static int result = MAX;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        start1 = new boolean[N];
        start2 = new boolean[N];
        end = new boolean[N];

        char[] tmp = br.readLine().toCharArray();

        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] == '1') {
                start1[i] = true;
                start2[i] = true;
            }
        }
        tmp = br.readLine().toCharArray();

        for (int i = 0; i < tmp.length; i++) {
            if (tmp[i] == '1') {
                end[i] = true;
            }
        }

        // System.out.println(Arrays.toString(start));
        // System.out.println(Arrays.toString(end));

        // 그리디, 각 전구는 한번이상 누를 필요가없다.
        // 앞에서부터 i-1 이 start와 end 와 다르면 i 를 누른다.
        // i 가 0 일때는 i - 1로 판정할 수 없기 때문에
        // 한번은 눌렀을때, 한번은 안눌렀을때로 가정하고 두번 돌린다.
        int count1 = 0;
        int count2 = 1;

        start2[0] = !start2[0];
        start2[1] = !start2[1];
        // start2 랑 count2 는 0번 인덱스 전구를 누른 상태로 시작한다.
        for (int i = 1; i < N; i++) {
            if (end[i - 1] != start1[i - 1]) {
                count1++;
                for (int j = i - 1; j <= i + 1 && j < N; j++) {
                    start1[j] = !start1[j];
                }
            }
            if (end[i - 1] != start2[i - 1]) {
                for (int j = i - 1; j <= i + 1 && j < N; j++) {
                    start2[j] = !start2[j];
                }
                count2++;
            }
            // System.out.println("");
            // System.out.println(Arrays.toString(start1));
            // System.out.println(Arrays.toString(start2));
        }
        if (start1[N - 1] == end[N - 1]) {
            result = count1;
        }
        if (start2[N - 1] == end[N - 1]) {
            result = Math.min(result, count2);
        }
        System.out.println(result == MAX ? "-1" : result);
    }
}
