import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        String[] sArr = s.split(" ");
        
        for(String text : sArr){
            if(text.length() == 0){
                sb.append(" ");
                continue;
            }
            String first = text.substring(0,1);
            String other = text.substring(1, text.length());
            
            sb.append(first.toUpperCase());
            sb.append(other.toLowerCase() + " ");
        }
        
        if(s.length() == sb.length()) answer = sb.toString();
        else answer = sb.toString().substring(0, s.length());
        
        return answer;
    }
}