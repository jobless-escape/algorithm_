import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        Set<Integer> left = new HashSet();
        Map<Integer, Integer> right = new HashMap();
        for(int t: topping) {
            right.put(t, right.getOrDefault(t, 0) + 1);
        }
        int size = right.size();
        for(int t: topping){
            left.add(t);
            right.put(t, right.get(t) - 1);
            if(right.get(t) == 0) right.remove(t);
            if(left.size() == right.size()) answer++; 
            else if(left.size() > right.size()) break;
        }
        
        return answer;
    }
}