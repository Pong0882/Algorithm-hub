import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    private static int[] man = new int[9];
    private static int sumA;

    private static Queue<Integer> que = new PriorityQueue<>();

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int all = 0;
        for (int i = 0; i < 9; i++) {
            man[i] = Integer.parseInt(br.readLine());
            all += man[i];
        }
        sumA = all - 100;

        a: for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                if ((man[i] + man[j] == sumA)) {
                    for (int j2 = 0; j2 < 9; j2++) {
                        if (j2 == j || j2 == i) {
                            continue;
                        }
                        que.offer(man[j2]);
                    }
                    break a;
                }
            }
        }
        for (int i = 0; i < 7; i++) {

            System.out.println(que.poll());
        }

    }
}
