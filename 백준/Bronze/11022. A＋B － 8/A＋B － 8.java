import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 테스트 케이스의 개수 입력
        int T = scanner.nextInt();
        
        // 각 테스트 케이스마다 A, B 입력받고 결과 출력
        for (int i = 1; i <= T; i++) {
            int A = scanner.nextInt();
            int B = scanner.nextInt();
            int sum = A + B;
            System.out.println("Case #" + i + ": " + A + " + " + B + " = " + sum);
        }
        
        scanner.close();
    }
}
