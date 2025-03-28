import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 재환이가 낼 수 있는 "aah"
        String jaehwan = scanner.nextLine();
        // 의사가 요구하는 "aah"
        String doctor = scanner.nextLine();
        
        // 'h'는 마지막에 하나만 있으므로, a의 개수는 전체 길이 - 1
        int jaehwanCount = jaehwan.length() - 1;
        int doctorCount = doctor.length() - 1;
        
        // 재환이가 낼 수 있는 a의 개수가 의사가 요구하는 a의 개수보다 많거나 같으면 "go", 아니면 "no"
        if (jaehwanCount >= doctorCount) {
            System.out.println("go");
        } else {
            System.out.println("no");
        }
    }
}
