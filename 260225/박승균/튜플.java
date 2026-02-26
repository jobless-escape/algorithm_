import java.util.*;
class Solution {
    public int[] solution(String s) {
        int[] answer;
        s = s.substring(2, s.length() - 2);
        String[] parts = s.split("\\},\\{");
        Arrays.sort(parts, (a, b) -> a.length() - b.length());
        Set<Integer> set = new HashSet<>();
        List<Integer> list = new ArrayList<>();
        
        for (String p : parts) {
            String[] nums = p.split(",");
            
            for(String num : nums){
                int n = Integer.parseInt(num);

                if(!set.contains(n)){
                    set.add(n);
                    list.add(n);
                }
            }
        }
        
        answer = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}