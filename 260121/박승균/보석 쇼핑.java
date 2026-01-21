import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        int left = 0;
        int right = 0;
        
        Set<String> gemset = new HashSet<>();
        for (String g : gems) gemset.add(g);
        int totalType = gemset.size();  // 채우고 사이즈 구하기
        
        Map<String, Integer> map = new HashMap<>();
        int minLen = Integer.MAX_VALUE;
        
        while(right < gems.length){
            map.put(gems[right], map.getOrDefault(gems[right], 0) + 1); // rigth 이동
            while(map.size() == totalType) { // 다 포함했으면 
                if (right - left + 1 < minLen) { // min 판단하고
                    minLen = right - left + 1;
                    answer[0] = left + 1;
                    answer[1] = right + 1;
                }
                
                map.put(gems[left], map.get(gems[left]) - 1); // left 이동
                if (map.get(gems[left]) == 0) {
                    map.remove(gems[left]);
                }
                left++;
            }
            right++;
        }
        
        
        
        return answer;
    }
}