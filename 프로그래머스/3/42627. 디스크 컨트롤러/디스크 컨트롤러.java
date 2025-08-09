import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a,b) -> Integer.compare(a[0] , b[0]));
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1] , b[1]));
        
        int n = jobs.length;
        int idx = 0;
        long time = 0;
        long total = 0;
        
        while(idx < n || !pq.isEmpty()){
            
            while(idx < n && jobs[idx][0] <= time){
                pq.offer(jobs[idx++]);
            }
            
            if(!pq.isEmpty()){
                int[] cur = pq.poll();
                time += cur[1];
                total += (time - cur[0]);
            } else {
                if (idx < n) time = jobs[idx][0];
            }
        }
        
        return (int)(total/n);
    }
}