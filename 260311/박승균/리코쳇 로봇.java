import java.util.*;

class Solution {
    int n, m;
    int[][] dx = {{1, 0}, {-1, 0}};
    int[][] dy = {{0, -1}, {0, 1}};
    
    public int solution(String[] board) {
        int answer = -1;
        n = board.length;
        m = board[0].length();
        int desx = 0, desy = 0, startx = 0, starty = 0;
        char[][] grid = new char[n][m];
        for(int i = 0; i < n; i++){
            char[] arr = board[i].toCharArray();
            for(int j = 0; j < m; j++){
                grid[i][j] = arr[j];
                if(arr[j] == 'G'){
                    desx = i; desy = j;
                }
                else if(arr[j] == 'R'){
                    startx = i; starty = j;
                }
            }
        }
        boolean visited[][] = new boolean[n][m];
        
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{startx, starty, 0});
        visited[startx][starty] = true;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curx = cur[0];
            int cury = cur[1];
            int cost = cur[2];
            if(curx == desx && cury == desy) return cost;
            
            for(int[] d: dx){
                int nx = curx;
                while(!isEdge(nx + d[0], cury) && grid[nx + d[0]][cury] != 'D'){
                    nx += d[0];
                }
                if(!isEdge(nx, cury) && !visited[nx][cury]){
                    q.add(new int[]{nx, cury, cost + 1});
                    visited[nx][cury] = true;
                }
            }
            for(int[] d: dy){
                int ny = cury;
                while(!isEdge(curx, ny + d[1]) && grid[curx][ny + d[1]] != 'D'){
                    ny += d[1];
                }
                if(!isEdge(curx, ny) && !visited[curx][ny]){
                    q.add(new int[]{curx, ny, cost + 1});
                    visited[curx][ny] = true;
                }
            }
        }
        
        return answer;
    }
    
    boolean isEdge(int x, int y){
        return x < 0 || x >= n || y < 0 || y >= m;
    }
}