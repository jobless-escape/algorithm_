import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        
        Stack<Integer> stack = new Stack<>();
        
        for(int i = 0; i < number.length(); i++){
            char ch = number.charAt(i);
            int n = change(ch);
            
            if(stack.isEmpty()){
                stack.push(n);
            }
            else {
                while(!stack.isEmpty() && stack.peek() < n && k > 0){
                    stack.pop();
                    k--;
                }
                stack.push(n);
            }
        }
        
        //마지막 처리
        while (k > 0) {
            stack.pop();
            k--;
        }
        
        StringBuilder sb = new StringBuilder();
        for (int num : stack) {
            sb.append(num);
        }
        answer = sb.toString();
        
        return answer;
    }
    
    public int change(char ch) {
        // char => int
        return ch - '0';
    }

}