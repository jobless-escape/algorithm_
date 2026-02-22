import java.util.*;

class Solution {
    static int[] maxOil;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    public int solution(int[][] land) {
        maxOil = new int[land[0].length];
        Arrays.fill(maxOil, 0);
        visited = new boolean[land.length][land[0].length];
        
        for(int i = 0 ; i < land[0].length ; i++){
            for(int j = 0 ; j < land.length ; j++){
                if(land[j][i] > 0 && !visited[j][i]) bfs(land, j, i);
            }
        }
        
        Arrays.sort(maxOil);
        
        return maxOil[maxOil.length - 1];
    }
    
    static void bfs(int[][] land, int y, int x){
        HashSet<Integer> hs = new HashSet<>();
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {y, x});
        visited[y][x] = true;
        int oil = 0;
        
        while(!q.isEmpty()){
            int[] now = q.poll();
            oil++;
            hs.add(now[1]);
            
            for(int i = 0 ; i < 4 ; i++){
                int nx = now[1] + dx[i];
                int ny = now[0] + dy[i];
                
                if(nx < 0 || ny < 0 || nx >= land[0].length || ny >= land.length) continue;
                
                if(land[ny][nx] > 0 && !visited[ny][nx]){
                    visited[ny][nx] = true;
                    q.offer(new int[] {ny, nx});
                }
            }
        }
        
        for(int index : hs){
            maxOil[index] += oil;
        }
    }
}