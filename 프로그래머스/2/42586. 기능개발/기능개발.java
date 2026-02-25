import java.util.*;

class Solution {
    static int N;
    public int[] solution(int[] progresses, int[] speeds) {
        N = progresses.length;
        int[] answer = {};
        List<Integer> list = new ArrayList<>();
        boolean[] finish = new boolean[N];
        boolean flag = false;
        int index = 0;
        // 이거 하루씩 하려면  너무 시간 오래걸리나.. 
        // 실제로 올리는게아니라 남은 날짜를 
        // 계산해서 사용하는게 좋겠다.
        int[] days = new int[N];
        for(int i = 0 ; i < N ; i++){
            int tmp = 100 - progresses[i];
            days[i] = (int) Math.ceil((double)tmp/speeds[i]);
        }    
        
        System.out.print(Arrays.toString(days));
        
        // 뭉덩이씩 빼는것도 좋은데 그냥 O(N)으로 주고싶으니까 day를 계속 누적하자
        int day = days[0];  
        int count = 1;    

        for (int i = 1; i < N; i++) {
            if (days[i] <= day) {
                count++;
            } else {
                list.add(count);
                day = days[i];
                count = 1;
            }
        }
        list.add(count);
        
        answer = new int[list.size()];
        for(int i = 0 ; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
    static boolean isOut(int a){
        return a >= N;
    }
}