import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static int[] indeg;
    static ArrayList<Integer>[] list;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));;
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        indeg = new int[N+1];
        list = new ArrayList[N+1];
        for (int i = 0; i < N+1; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            int n1 = Integer.parseInt(st.nextToken());
            for (int j = 1; j < tmp; j++) {
                int n2 = Integer.parseInt(st.nextToken());
                list[n1].add(n2);
                n1 = n2;
                indeg[n2]++;
            }
        }
        // System.out.println(Arrays.toString(indeg));
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int i = 1; i < N+1; i++) {
            if(indeg[i]==0){
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int cur = q.poll();
            N--;
            sb.append(cur).append("\n");
            for (Integer next : list[cur]) {
                if(--indeg[next]==0){
                    q.add(next);
                }
            }
        }
        System.out.println(N==0?sb:0);
    }
}
