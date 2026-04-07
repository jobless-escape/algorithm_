import java.util.*;

class Solution {
    int len;
    
    public int solution(String s) {
        int answer = 0;
        
        len = s.length();
        
        for(int i = 0; i < len; i++){
            if(isRight(s)) answer++;
            s = s.substring(1) + s.charAt(0);
        }
        
        return answer;
    }
    
    public boolean isRight(String s){
        Stack<Character> stack = new Stack<>();
        char[] arr = s.toCharArray();
        for(int i = 0; i < len; i++){
            char ch = arr[i];
            if(ch == '[' || ch == '{' || ch == '(') {
                stack.push(ch);
            }
            else {
                if(stack.isEmpty()) return false;
                
                char tmp = stack.pop();
                
                if(ch == ')' && tmp != '(') return false;
                if(ch == ']' && tmp != '[') return false;
                if(ch == '}' && tmp != '{') return false;
            }
        }
        
        return stack.isEmpty();
    }
}