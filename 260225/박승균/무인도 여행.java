import java.util.*;

class Solution {
    static boolean[][] visited;
    static char[][] maps;
    static int[][] dxdy = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static int n, m;
    
    public int[] solution(String[] maps) {
        int[] answer = {};
        n = maps.length; m = maps[0].length();
        visited = new boolean[n][m];
        this.maps = new char[n][m];
        for (int i = 0; i < maps.length; i++) {
            this.maps[i] = maps[i].toCharArray();
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!visited[i][j] && this.maps[i][j] != 'X'){
                    pq.add(calc(i, j));
                }
            }
        }
        
        answer = new int[pq.size()];
        int i = 0;
        if(pq.size() == 0) return new int[]{ -1 };
        while(!pq.isEmpty()) answer[i++] = pq.poll();
        
        return answer;
    }
    
    static int calc(int x, int y){
        int cnt = maps[x][y] - '0';
        visited[x][y] = true;

        for(int[] d: dxdy){
            int nx = x + d[0];
            int ny = y + d[1];

            if(!isEdge(nx, ny) && !visited[nx][ny] && maps[nx][ny] != 'X'){
                cnt += calc(nx, ny);
            }
        }
        
        return cnt;
    }
    
    static boolean isEdge(int x, int y){
        return x < 0 || x >= n || y < 0 || y >= m;
    }
}