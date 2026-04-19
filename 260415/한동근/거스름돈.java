class Solution {
    static final int MOD = 1_000_000_007;
    
    public int solution(int n, int[] money) {
        int[][] dp = new int[money.length + 1][n + 1];
        dp[0][0] = 1;
        
        for(int i = 1 ; i <= money.length ; i++){
            for(int j = 0 ; j <= n ; j++){
                dp[i][j] = dp[i-1][j];
                if(j >= money[i-1]){
                    dp[i][j] += dp[i][j - money[i-1]];
                    dp[i][j] %= MOD;
                }
            }
        }
        return dp[money.length][n];
    }
}