import java.util.*;

class Solution {
    public int solution(int[] money) {
        int answer = Integer.MIN_VALUE;
        int[] dp = new int[money.length];

        dp[0] = money[0];
        dp[1] = money[1];

        for(int i = 2 ; i < money.length; i++){
            if(i % 2 == 0) dp[i] = dp[i - 2] + money[i];
            else dp[i] = dp[i - 2] + money[i];
        }

        answer = Math.max(dp[dp.length - 1], dp[dp.length - 2]);

        return answer;
    }
}