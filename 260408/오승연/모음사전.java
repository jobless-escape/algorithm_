class Solution {
    
    public int solution(String word) {
        int answer = 0;
        int total= 3905;
        String aeiou="AEIOU";
        
        for(String str: word.split("")){
            //781, 156, 31, 6, 1
            //각 자리에서 한 글자가 바뀌면 건너뛰는 단어 개수
            total/= 5;
            answer+= total*aeiou.indexOf(str)+1;
        }
        
        return answer;
    }
}
