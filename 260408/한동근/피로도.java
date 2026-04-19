class Solution {
    static int answer;
    static boolean[] visited;
    
    public int solution(int k, int[][] dungeons) {
        answer = Integer.MIN_VALUE;
        visited = new boolean[dungeons.length];
        
        dfs(k, 0, 0, dungeons);
        return answer;
    }
    
    static void dfs(int k, int now, int idx, int[][] dungeons){
        if(idx == dungeons.length){
            answer = Math.max(answer, now);
            return;
        }
        
        for(int i = 0 ; i < dungeons.length ; i++){
            if(!visited[i]){
                if(k >= dungeons[i][0]){
                    visited[i] = true;
                    dfs(k - dungeons[i][1], now + 1, idx + 1, dungeons);
                }
                else{
                    visited[i] = true;
                    dfs(k, now, idx + 1, dungeons);
                }
                visited[i] = false;
            }
        }
    }
}