import java.util.*;

class Solution {
    public int solution(int[] elements) {
        HashSet<Integer> set = new HashSet<>();
        
        int[] circle = new int[elements.length * 2];
        
        for(int i = 0 ; i < circle.length / 2 ; i++){
            circle[i] = elements[i];
            circle[i + elements.length] = elements[i];
        }
        
        for(int len = 1 ; len <= elements.length ; len++){
            int sum = 0;
            
            for(int i = 0 ; i < len ; i++) sum += circle[i];
            
            set.add(sum);
            
            for(int i = len ; i < circle.length ; i++){
                sum -= circle[i - len];
                sum += circle[i];
                set.add(sum);
            }
        }
        
        return set.size();
    }
}