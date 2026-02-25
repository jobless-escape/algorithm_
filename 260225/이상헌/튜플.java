import java.util.*;

class Solution {
    public int[] solution(String s) {
        s = s.substring(2, s.length() - 2);

        String[] sets = s.split("\\},\\{");

        Arrays.sort(sets, (a, b) -> a.length() - b.length());

        int[] result = new int[sets.length];
        Set<Integer> visited = new HashSet<>();

        for (int i = 0; i < sets.length; i++) {
            String[] nums = sets[i].split(",");
            for (String num : nums) {
                int n = Integer.parseInt(num);
                if (!visited.contains(n)) {
                    result[i] = n;
                    visited.add(n);
                    break;
                }
            }
        }

        return result;
    }
}