import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        
        int len = number.length() - k;
        
        for(int i = 0 ; i < number.length() ; i++){
            char num = number.charAt(i);
            while(!stack.isEmpty() && k > 0 && stack.peek() < num){
                stack.pop();
                k--;
            }
            stack.push(num);
        }
        
        while(!stack.isEmpty()) sb.append(stack.pop());
        
        return sb.reverse().toString().substring(0, len);
    }
}