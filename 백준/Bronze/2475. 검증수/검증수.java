import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {
    public static void main(String[] args) throws IOException {
     
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] paper = new int[5];
        
        int result = 0;
        for (int i = 0; i < 5; i++) {
            paper[i] = Integer.parseInt(st.nextToken());
            result += paper[i]*paper[i];
        }
        System.out.println(result%10);
    }
}