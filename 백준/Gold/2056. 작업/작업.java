
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] indeg;
    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static ArrayList<Integer>[] list;
    static int[] time;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        indeg = new int[N + 1];
        time = new int[N + 1];
        dp = new int[N + 1];
        list = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i < N + 1; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            dp[i] = time[i];
            int indegN = Integer.parseInt(st.nextToken());
            for (int j = 0; j < indegN; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                list[tmp].add(i);
                indeg[i]++;
            }
        }

        // System.out.println(Arrays.toString(dp));
        for (int i = 1; i < N + 1; i++) {
            if (indeg[i] == 0) {
                pq.add(i);
            }
        }
        while (!pq.isEmpty()) {
            int n = pq.poll();
            for (int next : list[n]) {
                dp[next] = Math.max(dp[next], dp[n] + time[next]);
                if (--indeg[next] == 0) {
                    pq.add(next);
                }
            }
        }
        // System.out.println(Arrays.toString(dp));
        int result = 0;
        for (int i = 1; i < N + 1; i++) {
            result = Math.max(result, dp[i]);
        }
        System.out.println(result);
    }
}
