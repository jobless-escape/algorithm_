import java.util.*;

class Solution {
    public int solution(int[] topping) {
        HashSet<Integer> a = new HashSet<>();
        HashMap<Integer, Integer> b = new HashMap<>();
        int answer = 0;
        
        for(int i = 0 ; i < topping.length ; i++){
            b.put(topping[i], b.getOrDefault(topping[i], 0) + 1);
        }
        
        for(int i = 0 ; i < topping.length ; i++){
            if(a.size() == b.size()) answer++;
            a.add(topping[i]);
            
            if(b.containsKey(topping[i])){
                if(b.get(topping[i]) == 1) b.remove(topping[i]);
                else b.put(topping[i], b.get(topping[i]) -1);
            }
        }
        return answer;
    }
}