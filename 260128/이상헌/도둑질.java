import java.io.*;
import java.lang.*;

class Solution {
    public int solution(int[] money) {
        int n = money.length;

        int case1 = rob(money, 0, n - 2);
        int case2 = rob(money, 1, n - 1);

        return Math.max(case1, case2);
    }

    private int rob(int[] money, int start, int end) {
        int prev2 = 0;  // dp[i-2]
        int prev1 = 0;  // dp[i-1]

        for(int i = start; i <= end; i++) {
            int current = Math.max(prev1, prev2 + money[i]);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}