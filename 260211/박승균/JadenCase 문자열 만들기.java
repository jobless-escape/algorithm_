import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        char[] arr = s.toCharArray();
        for(int i = 0; i < arr.length; i++){
            char ch = arr[i];
            if(i == 0 || arr[i - 1] == ' '){
                if('a' <= ch && ch <= 'z') ch -= 32;
            }
            else if('A' <= ch && ch <= 'Z') ch += 32;
            answer += ch;
        }
        
        return answer;
    }
}