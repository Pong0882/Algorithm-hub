import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N;
    static int result = 0;
    static boolean[] visitedC;
    static boolean[] visitedDiag; 
    static boolean[] visitedDiag2; 

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        visitedC = new boolean[N];
        visitedDiag = new boolean[2 * N - 1];
        visitedDiag2 = new boolean[2 * N - 1];

        // dfs 백트래킹, 대각 visited 활용
        dfs(0);
        System.out.println(result);
    }

    static void dfs(int r) {
        if (r == N) {
            result++;
            return;
        }

        for (int c = 0; c < N; c++) {
            if (visitedC[c] || visitedDiag[r + c] || visitedDiag2[r - c + N - 1]) {
                continue;
            }

            visitedC[c] = true;
            visitedDiag[r + c] = true;
            visitedDiag2[r - c + N - 1] = true;

            dfs(r + 1);

            visitedC[c] = false;
            visitedDiag[r + c] = false;
            visitedDiag2[r - c + N - 1] = false;
        }
    }
}
