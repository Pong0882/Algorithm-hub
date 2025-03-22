import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N;

    static class meeting implements Comparable<meeting> {
        int s, e;

        public meeting(int s, int e) {
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(meeting o) {
            if (e != o.e) {
                return Integer.compare(e, o.e);
            }
            return Integer.compare(s, o.s);
        }

    }

    static PriorityQueue<meeting> pq = new PriorityQueue<>();
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.add(new meeting(s, e));
        }

        int last = 0;
        while (!pq.isEmpty()) {
            meeting cur = pq.poll();
            if (last <= cur.s) {
                last = cur.e;
                result++;
            }
        }
        System.out.println(result);
    }
}
