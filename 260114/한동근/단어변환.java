class Solution {
    static int answer;
    static boolean[] visited;
    
    public int solution(String begin, String target, String[] words) {
        answer = Integer.MAX_VALUE;
        visited = new boolean[words.length];
        
        dfs(begin, target, words, 0);
        
        if(answer == Integer.MAX_VALUE) answer = 0;

        return answer;
    }
    
    static void dfs(String begin, String target, String[] words, int count){
        if(begin.equals(target)){
            answer = Math.min(answer, count);
            return;
        }
        
        for(int i = 0 ; i < words.length ; i++){
            int notDuplCount = 0;
            String to = words[i];
            
            for(int j = 0 ; j < to.length() ; j++){
                if(begin.charAt(j) != to.charAt(j)){
                    notDuplCount++;
                }
            }
            
            if(notDuplCount == 1 && !visited[i]){
                visited[i] = true;
                dfs(to, target, words, count + 1);
                visited[i] = false;
            }
        }
    }
}