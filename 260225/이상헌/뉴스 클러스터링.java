import java.util.*;

class Solution {
    public int solution(String str1, String str2) {
        Map<String, Integer> mapA = makeMultiset(str1);
        Map<String, Integer> mapB = makeMultiset(str2);

        Set<String> allKeys = new HashSet<>();
        allKeys.addAll(mapA.keySet());
        allKeys.addAll(mapB.keySet());

        int intersection = 0;
        int union = 0;

        for (String key : allKeys) {
            int a = mapA.getOrDefault(key, 0);
            int b = mapB.getOrDefault(key, 0);
            intersection += Math.min(a, b);
            union += Math.max(a, b);
        }

        if (union == 0) return 65536;

        return (int) ((double) intersection / union * 65536);
    }

    private Map<String, Integer> makeMultiset(String str) {
        Map<String, Integer> map = new HashMap<>();
        str = str.toLowerCase();

        for (int i = 0; i < str.length() - 1; i++) {
            char c1 = str.charAt(i);
            char c2 = str.charAt(i + 1);

            if (Character.isLetter(c1) && Character.isLetter(c2)) {
                String key = "" + c1 + c2;
                map.put(key, map.getOrDefault(key, 0) + 1);
            }
        }
        return map;
    }
}