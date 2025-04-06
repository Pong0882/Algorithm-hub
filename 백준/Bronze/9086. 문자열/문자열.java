import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        for (int i = 0; i < T; i++) {
            String str = sc.next();
            // 문자열의 첫 글자와 마지막 글자를 출력
            System.out.println("" + str.charAt(0) + str.charAt(str.length() - 1));
        }
        sc.close();
    }
}
