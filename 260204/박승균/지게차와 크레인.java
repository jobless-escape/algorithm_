import java.util.*;

class Solution {
    static char[][] grid;
    static int[][] dxdy = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
    static int N, M;
    
    public int solution(String[] storage, String[] requests) {
        
        this.grid = new char[storage.length + 2][storage[0].length() + 2];
        for (int i = 0; i < grid.length; i++) {
            Arrays.fill(grid[i], '.');
        }
        for(int i = 0; i < storage.length; i++){
            String st = storage[i];
            for(int j = 0; j < st.length(); j++){
                grid[i + 1][j + 1] = st.charAt(j);
            }
        }
        N = grid.length; M = grid[0].length;
        
        for(String st: requests){
            boolean[][] visited = new boolean[N][M];
            visited[0][0] = true;
            char ch = st.charAt(0);
            if(st.length() == 2){
                removeChar(ch);
                continue;
            }
            
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[] {0, 0});
            while(!q.isEmpty()){
                int[] cur = q.poll();
                int curX = cur[0]; int curY = cur[1];
                
                for(int[] d: dxdy){
                    int nx = curX + d[0]; int ny = curY + d[1];
                    if(!isEdge(nx,ny) && !visited[nx][ny] && grid[nx][ny] == '.'){
                        q.offer(new int[] {nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
            
            removeVisited(ch, visited);
        }
        
        
        
        
        return countContainer();
    }
    
    public int countContainer(){
        int cnt = 0;
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] != '.') cnt++;
            }
        }
        
        return cnt;
    }
    
    public void removeChar(char ch){
        for(int i = 0; i < grid.length; i++){
            for(int j = 0; j < grid[0].length; j++){
                if(grid[i][j] == ch) grid[i][j] = '.';
            }
        }
    }
    
    public boolean isEdge(int x, int y){
        return x < 0 || x >= N || y < 0 || y >= M;
    }
    
    public void removeVisited(char ch, boolean[][] visited){
        List<int[]> removeList = new ArrayList<>();

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(grid[i][j] == ch){
                    for(int[] d : dxdy){
                        int nx = i + d[0];
                        int ny = j + d[1];
                        if(!isEdge(nx, ny) && visited[nx][ny]){
                            removeList.add(new int[]{i, j});
                            break;
                        }
                    }
                }
            }
        }

        // 한 번에 제거
        for(int[] p : removeList){
            grid[p[0]][p[1]] = '.';
        }
    }
}