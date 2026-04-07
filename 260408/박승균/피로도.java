class Solution {
    int[][] dungeons;
    int max = Integer.MIN_VALUE;
    boolean visited[];
    int n;
    
    public int solution(int k, int[][] dungeons) {
        this.dungeons = dungeons;
        n = dungeons.length;
        visited = new boolean[n];
        
        dfs(0, k);
        
        return max;
    }
    
    public void dfs(int cnt, int tired){
        if(cnt == n || max == n) {
            max = n;
            return;
        }
        max = Math.max(max, cnt);
        
        for(int i = 0; i < n; i++){
            if(!visited[i] && tired >= dungeons[i][0]){
                visited[i] = true;
                dfs(cnt + 1, tired - dungeons[i][1]);
                visited[i] = false;
            }
        }
    }
}