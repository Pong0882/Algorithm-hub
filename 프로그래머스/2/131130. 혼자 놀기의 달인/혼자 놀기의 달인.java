import java.util.*;

class Solution {
    static int[] result;
    static boolean[] visited;
    static int[] paper;
    static int N;
    static int cnt;
    
    public int solution(int[] cards) {
        paper = cards;
        N = paper.length;
        
        visited = new boolean[N];
        result = new int[N];
        
        for(int i = 0 ; i < N ; i++){
            if(!visited[i]){
                DFS(i, 0);
            }
        }
        
        Arrays.sort(result);
        
        return result[N-1] * result[N-2];
    }
    
    private static void DFS(int i, int depth){
        if(visited[i]){
            result[cnt++] = depth;
            return;
        }
        visited[i] = true;
        DFS(paper[i]-1,depth+1);
    }
}