import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {

        int[] dp = new int[m];
        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        for (int[] item : info) {
            int a = item[0];
            int b = item[1];

            for (int i = m - 1; i >= 0; i--) {
                if (dp[i] == Integer.MAX_VALUE) continue;

                // B가 훔치기
                if (i + b < m) {
                    dp[i + b] = Math.min(dp[i + b], dp[i]);
                }

                // A가 훔치기
                if (dp[i] + a < n) {
                    dp[i] = dp[i] + a;
                } else {
                    dp[i] = Integer.MAX_VALUE;
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int a : dp) {
            answer = Math.min(answer, a);
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}