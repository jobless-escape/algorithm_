import java.util.*;

class Solution {
    int solution(int[][] land) {
        int answer = 0;
        int n = land.length;
        int m = 4;
        int[] dp = new int[m];

        for(int i = 0 ; i < m ; i++){
            dp[i] = land[0][i];
        }

        for(int i = 1 ; i < n ; i++){
            int[] next = new int[m];
            for(int j = 0 ; j < m ; j++){
                int case1 = 0;
                for(int  k = 0 ; k < m ; k++){
                    if(k == j) continue;
                    case1 = Math.max(case1, dp[k] + land[i][j]);
                }
                next[j] = case1;
            }
            dp = next;
        }


        for(int i = 0 ; i < m ; i++){
            answer = Math.max(answer, dp[i]);
        }
        return answer;
    }
}