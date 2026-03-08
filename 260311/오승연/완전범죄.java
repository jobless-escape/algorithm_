import java.util.*;

class Solution {
    static int INF=100000;
    
    public int solution(int[][] info, int n, int m) {
        int size = info.length;
        int [][] dp = new int [size+1][m];
        for(int i = 0; i <= size; i++){
            Arrays.fill(dp[i], INF);
        }
        
        dp[0][0] = 0; // dp[3][5] = 7 이면 B흔적 5, A흔적 최소 7이라는 뜻
        
        for(int i = 1; i <= size; i++){
            int a = info[i-1][0];
            int b = info[i-1][1];
            
            for(int j = 0; j < m; j++){
                // a 선택
                dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + a); // 이전 상태에서 b흔적 j그대로, a흔적 a 추가
                
                // b 선택
                if(j + b < m){
                    dp[i][j + b] = Math.min(dp[i][j + b], dp[i-1][j]); //현재 물건 B가 훔쳐서 b흔적증가, a흔적그대로
                }
            }
        }

        int min = INF;
        for(int j = 0; j < m; j++){
            min = Math.min(dp[size][j], min);
        }
        
        if(min>=n){
            return -1;
        }else{
            return min;
        }
    }
}
