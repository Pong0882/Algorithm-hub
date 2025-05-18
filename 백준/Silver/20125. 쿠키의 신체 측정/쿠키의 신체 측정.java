import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[][] map;
    static StringBuilder sb = new StringBuilder();
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int heartX = -1;
        int heartY = -1;

        a: for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == '*') {
                    heartX = i + 1;
                    heartY = j;
                    break a;
                }
            }
        }

        // 왼팔
        int leftArm = 0;
        for (int j = heartY - 1; j >= 0; j--) {
            if (map[heartX][j] == '*')
                leftArm++;
            else
                break;
        }

        // 오른팔
        int rightArm = 0;
        for (int j = heartY + 1; j < N; j++) {
            if (map[heartX][j] == '*')
                rightArm++;
            else
                break;
        }

        // 허리
        int waist = 0;
        int waistEndX = heartX;
        for (int i = heartX + 1; i < N; i++) {
            if (map[i][heartY] == '*') {
                waist++;
                waistEndX = i;
            } else
                break;
        }

        // 왼다리
        int leftLeg = 0;
        for (int i = waistEndX + 1; i < N; i++) {
            if (map[i][heartY - 1] == '*')
                leftLeg++;
            else
                break;
        }

        // 오른다리
        int rightLeg = 0;
        for (int i = waistEndX + 1; i < N; i++) {
            if (map[i][heartY + 1] == '*')
                rightLeg++;
            else
                break;
        }

        sb.append((heartX + 1)).append(" ").append((heartY + 1)).append("\n");
        sb.append(leftArm).append(" ")
                .append(rightArm).append(" ")
                .append(waist).append(" ")
                .append(leftLeg).append(" ")
                .append(rightLeg);

        System.out.println(sb);
    }
}
