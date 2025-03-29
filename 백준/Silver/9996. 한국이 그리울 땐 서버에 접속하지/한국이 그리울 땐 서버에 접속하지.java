import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        String code = br.readLine();
        String cut[] = code.split("\\*");
        for (int i = 0; i < n; i++) {
            String hello = br.readLine();
            if (hello.length() < cut[0].length() + cut[1].length()) {
                sb.append("NE").append("\n");
                continue;
            }
            String a = hello.substring(0, cut[0].length());
            String b = hello.substring(hello.length() - cut[1].length(), hello.length());
            sb.append(a.equals(cut[0]) && b.equals(cut[1]) ? "DA" : "NE").append("\n");
        }
        System.out.println(sb);
    }
}
