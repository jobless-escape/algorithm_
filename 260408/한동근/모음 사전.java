class Solution {
    static int answer;
    static int count;
    static String[] alphabet = {"A", "E", "I", "O", "U"};
    public int solution(String word) {
        answer = 0;
        count = 0;
        dfs(word, "");
        return answer;
    }
    
    static void dfs(String word, String now){
        if(word.equals(now)) answer = count;
        
        if(now.length() == 5 || answer != 0) return;
        
        for(int i = 0 ; i < 5 ; i++){
            count++;
            dfs(word, now + alphabet[i]);
        }
    }
}