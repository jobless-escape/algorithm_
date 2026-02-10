class Solution {
    public int solution(int m, int n, int[][] puddles) {
        boolean[][] water = new boolean[n][m];
        // 0 - based
        for(int[] p : puddles){
            water[p[1]-1][p[0]-1] = true;
        }
        int[][] dp = new int[n][m];
        dp[0][0] = 1;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                
                if (water[i][j]) {
                    dp[i][j] = 0;
                    continue;
                }
                
                if(i > 0) dp[i][j] += dp[i-1][j] % 1000000007;
                if(j > 0) dp[i][j] += dp[i][j-1] % 1000000007;
            }
        }
        
        return dp[n-1][m-1] % 1000000007;
    }
}