import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int[] dp = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int[] item : info) {
            int a = item[0], b = item[1];
            int[] next = new int[n];
            Arrays.fill(next, Integer.MAX_VALUE);

            for (int j = 0; j < n; j++) {
                if (dp[j] == Integer.MAX_VALUE) continue;

                if (j + a < n) {
                    next[j + a] = Math.min(next[j + a], dp[j]);
                }

                if (dp[j] + b < m) {
                    next[j] = Math.min(next[j], dp[j] + b);
                }
            }
            dp = next;
        }

        for (int j = 0; j < n; j++) {
            if (dp[j] != Integer.MAX_VALUE) {
                return j;
            }
        }

        return -1;
    }
}