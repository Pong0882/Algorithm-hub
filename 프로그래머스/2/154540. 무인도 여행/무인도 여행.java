import java.util.*;

class Solution {
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> resultList = new ArrayList<>();
    static int[] result;
    static int[] dr = {0,1,0,-1};
    static int[] dc = {-1,0,1,0};
    static int N,M;
    
    public int[] solution(String[] maps) {
        N = maps.length;
        M = maps[0].length();
        
        map = new int[N][M];
        visited = new boolean[N][M];
        
        for(int i = 0 ; i < N; i++){
            char[] tmp = maps[i].toCharArray();
            for(int j = 0 ; j < M ; j++){
                 if (tmp[j] == 'X') {
                    map[i][j] = 0;
                    visited[i][j] = true;
                } else {
                    map[i][j] = tmp[j] - '0'; 
                }
            }
        }
        for(int i = 0 ; i < N; i++){
            for(int j = 0 ; j < M ; j++){
                if(!visited[i][j]){
                    resultList.add(BFS(i,j));
                }
            }
        }
        if(resultList.isEmpty()){
            return new int[]{-1};
        }
        Collections.sort(resultList);
        // System.out.print(resultList);
        result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }
    
    private static int BFS(int i, int j){
        Deque<int[]> que = new ArrayDeque<>();
        que.add(new int[] { i , j });
        visited[i][j] = true;
        int sum = 0;
        
        while(!que.isEmpty()){
            int[] tmp = que.poll();
            int r = tmp[0];
            int c = tmp[1];
            sum += map[r][c];
            
            for(int d = 0 ; d < 4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(!boundaryCheck(nr,nc)){
                    continue;
                }
                if(visited[nr][nc]){
                    continue;
                }
                que.add(new int[] {nr,nc});
                visited[nr][nc] = true;
            }
        }
        return sum;
    }
    
    private static boolean boundaryCheck(int r, int c){
        return r>=0&&c>=0&&r<N&&c<M;
    }
}