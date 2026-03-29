import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int n = triangle.length;
        int[][] dp = new int[n][n];
        for(int i = 0 ; i < n ; i++) Arrays.fill(dp[i], 0);
        
        dp[0][0] = triangle[0][0];
        dp[1][0] = triangle[0][0] + triangle[1][0];
        dp[1][1] = triangle[0][0] + triangle[1][1];
        
        for(int i = 2 ; i < n ; i++){
            for(int j = 0 ; j < triangle[i].length; j++){
                if(j == 0) dp[i][j] = dp[i-1][j] + triangle[i][j];
                else if(j == i) dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                else {
                    dp[i][j] = Math.max(dp[i-1][j] + triangle[i][j], dp[i-1][j-1] + triangle[i][j]);
                }
            }
        }
        
        for(int i = 0 ; i < n ; i++){
            answer = Math.max(answer, dp[n - 1][i]);
        }
        return answer;
    }
}