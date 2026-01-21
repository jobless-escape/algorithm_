import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        HashSet<String> hs = new HashSet<>();
        HashMap<String, Integer> hm = new HashMap<>();
        int[] answer = new int[2];
        
        for(String gem : gems) hs.add(gem);
        
        int size = hs.size();
        
        int left = 0;
        int bestLength = Integer.MAX_VALUE;
        
        for(int right = 0 ; right < gems.length ; right++){
            hm.put(gems[right], hm.getOrDefault(gems[right], 0) + 1);
            
            while(hm.size() == size){
                if(right - left < bestLength){
                    bestLength = right - left;
                    answer[0] = left + 1;
                    answer[1] = right + 1;
                }
                
                hm.put(gems[left], hm.get(gems[left]) - 1);
                if(hm.get(gems[left]) == 0) hm.remove(gems[left]);
                left++;
            }
        }
        return answer;
    }
}
