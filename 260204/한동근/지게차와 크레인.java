import java.util.*;

class Solution {
    static char[][] map;
    static int n, m, answer;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][] visited;
    
    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        answer = n * m;
        map = new char[n][m];
        
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                map[i][j] = storage[i].charAt(j);
            }
        }
        
        for(String request : requests){
            char op = request.charAt(0);
            if(request.length() == 1){
                jige(op);
            }
            else if(request.length() == 2){
                crane(op);
            }
        }
        
        return answer;
    }
    
    static void crane(char op){
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(map[i][j] == op) {
                    map[i][j] = '0';
                    answer--;
                }
            }
        }
    }
    static void jige(char op){
        visited = new boolean[n][m];
        
        for(int i = 0 ; i < m ; i++){
            if(!visited[0][i]) dfs(0, i, op);
            if(!visited[n-1][i]) dfs(n-1, i, op);
        }
        
        for(int i = 0 ; i < n ; i++){
            if(!visited[i][0]) dfs(i,0, op);
            if(!visited[i][m-1]) dfs(i, m-1, op);
        }
    }
    
    static void dfs(int x, int y, char op){
        visited[x][y] = true;
        
        if(map[x][y] == op){
            map[x][y] = '0';
            answer--;
            return;
        }
        
        if(map[x][y] == '0'){
            for(int i = 0 ; i < 4 ; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if(!visited[nx][ny]) {
                    dfs(nx, ny, op);
                }
            }
        }

    }
}