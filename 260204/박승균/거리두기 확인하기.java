import java.util.*;

class Solution {
    static int[][] near = {{0,1},{0,-1},{1,0},{-1,0}};
    static int[][] cross = {{1,1},{1,-1},{-1,1},{-1,-1}};
    static int[][] oneSpace = {{0,2},{0,-2},{2,0},{-2,0}};
    static int n;
    
    public int[] solution(String[][] places) {
        n = places.length;
        int[] answer = new int[n];
        
        for(int tc = 0; tc < n; tc++){
            char[][] grid = new char[5][5];
            //격자 char배열로 만들기
            for(int i = 0; i < 5; i++){
                String str = places[tc][i];
                for(int j = 0; j < 5; j++){
                    grid[i][j] = str.charAt(j);
                }
            }
            
            //격자 전달해서 판단
            answer[tc] = checkDistance(grid);
        }
        
        return answer;
    }
    
    public int checkDistance(char[][] grid){

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(grid[i][j] != 'P') continue;
                
                // near 확인
                for(int[] d: near){
                    int nx = i + d[0]; int ny = j + d[1];
                    if(isEdge(nx, ny)) continue;
                    char ch = grid[nx][ny];
                    
                    if(ch == 'P') return 0;
                }

                // cross 확인
                for(int[] d: cross){
                    int nx = i + d[0]; int ny = j + d[1];
                    if(isEdge(nx, ny)) continue;
                    char ch = grid[nx][ny];
                    
                    if(ch == 'P') {
                        // P있으면 그 사이 파티션 검사
                        if(grid[nx][j] != 'X' || grid[i][ny] != 'X') return 0;
                    }
                }

                // 한칸띈거 확인
                for(int[] d: oneSpace){
                    int nx = i + d[0]; int ny = j + d[1];
                    if(isEdge(nx, ny)) continue;
                    char ch = grid[nx][ny];
                    
                    if(ch == 'P') {
                        // P있으면 그 사이 파티션 검사
                        int mx = (nx + i) / 2; int my = (ny + j) / 2;
                        if(grid[mx][my] != 'X') return 0;
                    }
                }
            }
        }
        
        
        
        return 1;
    }
    
    public boolean isEdge(int x, int y){
        return x < 0 || x >= 5 || y < 0 || y >= 5;
    }
}