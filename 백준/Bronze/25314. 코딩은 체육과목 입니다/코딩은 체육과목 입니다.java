import java.util.*;

public class Main{
    public static void main (String[] args){
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //int n = Integer.parseInt(br.readline());
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int tmp = n/4;
        for(int i = 0 ; i <tmp ; i++){
            System.out.print("long ");
        }
        System.out.print("int");
    }
}