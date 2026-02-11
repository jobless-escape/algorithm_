import java.util.*;

class Solution {
    static int divideNum = 1_000_000_007;
    
    public int solution(int m, int n, int[][] puddles) {
        int[][] map = new int[n + 1][m + 1];
        
        for(int i = 0 ; i <= n ; i++){
            Arrays.fill(map[i], 0);
        }
        
        for(int[] puddle : puddles){
            map[puddle[1]][puddle[0]] = -1;
        }
        
        map[1][1] = 1;
        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j<= m ; j++){
                if(map[i][j] == -1) continue;
                
                if(map[i-1][j] > 0) map[i][j] = (map[i][j] + map[i-1][j]) % divideNum;
                if(map[i][j-1] > 0) map[i][j] = (map[i][j] + map[i][j-1]) % divideNum;
            }
        }
        
        return map[n][m];
    }
}