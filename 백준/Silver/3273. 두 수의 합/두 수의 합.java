import java.util.*;
import java.io.*;

/*
 * 세번째 아이디어 : 카운트배열로 바로 이용한다.
 */
public class Main {

    public static void main(String[] args) throws Exception {
       int n = read();
       int[] arr = new int[n];
       boolean[] cnt = new boolean[1000001];
       int ans = 0;

       for(int i = 0; i < n ; i++){
           int now = read();
           cnt[now] = true;
           arr[i] = now;
       }
       int x = read();

       for(int i = 0; i < n; i++){
           int idx = x - arr[i];
           if(idx > 0 && idx <= 1000000 && cnt[idx]) ans++;
       }

       System.out.println(ans/2);
    }
    public static int read() throws Exception{
        int n = 0;
        int cur;
        boolean isNumber = false;
        boolean isNegative = false;
        while(true){
            cur = System.in.read();
            if(cur == '-'){
                isNegative = true;
            }
            else if(cur <= 32){
                if(isNumber){
                    return isNegative ? -n : n;
                }
            }
            else{
                isNumber = true;
                n = (n<<3) + (n<<1) + (cur&15);
            }
        }
    }
}