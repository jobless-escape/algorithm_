import java.util.*;

class Solution {
    public int[] solution(String s) {
        s = s.substring(2, s.length() - 2);
        String[] tuples = s.split("\\},\\{");
        HashMap<Integer, Integer> hm = new HashMap<>();

        for(String tuple : tuples){
            String[] nums = tuple.split(",");

            for(String num : nums){
                int a = Integer.parseInt(num);
                hm.put(a, hm.getOrDefault(a, 0) + 1);
            }
        }

        ArrayList<Integer> list = new ArrayList<>(hm.keySet());
        list.sort((a, b) -> hm.get(b) - hm.get(a));

        return list.stream().
                mapToInt(i -> i).
                toArray();
    }
}