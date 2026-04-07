class Solution {
    int cnt = 0;
    int answer = 0;
    String word;
    char[] arr = {'A', 'E', 'I', 'O', 'U'};
    
    public int solution(String word) {
        this.word = word;
        dfs("");
        return cnt;
    }
    
    void dfs(String str){
        if(str.equals(word)){
            answer = cnt;
            return;
        }
        
        if(str.length() == 5) return;
        
        for(int i = 0; i < 5; i++){
            cnt++;
            dfs(str + arr[i]);
            
            if(answer != 0) return;
        }
    }
}