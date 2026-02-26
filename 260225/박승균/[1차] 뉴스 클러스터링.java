import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        
        //str 전처리
        Map<String, Integer> map1 = preprocess(str1);
        Map<String, Integer> map2 = preprocess(str2);
        
        int intersection = 0;
        int union = 0;
        // 교집합
        for (String key : map1.keySet()) {
            if (map2.containsKey(key)) {
                intersection += Math.min(map1.get(key), map2.get(key));
            }
        }
        
        //합집합
        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        for (String key : allKeys) {
            int c1 = map1.getOrDefault(key, 0);
            int c2 = map2.getOrDefault(key, 0);
            union += Math.max(c1, c2);
        }
        
        
        if(intersection == 0 && union == 0) return 65536;
        return (int)((double)intersection / union * 65536);
    }
    
    static Map<String, Integer> preprocess(String str){
        Map<String, Integer> map = new HashMap<>();
        str = str.toLowerCase();
        for (int i = 0; i < str.length() - 1; i++) {
            char a = str.charAt(i);
            char b = str.charAt(i+1);

            if (Character.isLetter(a) && Character.isLetter(b)) {
                String two = str.substring(i, i + 2);
                map.put(two, map.getOrDefault(two, 0) + 1);
            }
        }
        
        return map;
    }
}