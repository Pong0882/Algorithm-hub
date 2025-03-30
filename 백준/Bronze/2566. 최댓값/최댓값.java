import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int max = -1;       // 문제 조건 상 수는 0 이상이므로 -1로 초기화
        int maxRow = 0;
        int maxCol = 0;
        
        // 9행 9열의 입력을 받으며 최댓값과 위치를 찾음
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int num = sc.nextInt();
                if (num > max) {
                    max = num;
                    maxRow = i;
                    maxCol = j;
                }
            }
        }
        sc.close();
        
        // 행과 열 번호는 1부터 시작하므로 +1씩 함
        System.out.println(max);
        System.out.println((maxRow + 1) + " " + (maxCol + 1));
    }
}
