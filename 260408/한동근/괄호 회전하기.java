import java.util.*;

class Solution {
    public int solution(String s) {
        String s2 = s + s;
        int answer = 0;
        Stack<Character> stack = new Stack<>();
        
        for(int i = 0 ; i < s.length() ; i++){
            stack.clear();
            
            for(int j = i ; j < i + s.length(); j++){
                char c = s2.charAt(j);
                
                if(stack.isEmpty()){
                    stack.push(c);
                }
                else{
                    if(stack.peek() == '(' && c == ')') stack.pop();
                    else if(stack.peek() == '{' && c == '}') stack.pop();
                    else if(stack.peek() == '[' && c == ']') stack.pop();
                    else stack.push(c);
                }
            }
            
            if(stack.isEmpty()) answer++;
        }
        return answer;
    }
}