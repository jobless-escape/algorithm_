import java.util.*;

class Solution {
    public int[] solution(String[] gems) {

        Set<String> gemTypes = new HashSet<>(Arrays.asList(gems));
        int totalTypes = gemTypes.size();

        Map<String, Integer> window = new HashMap<>();
        int left = 0;
        int minLength = Integer.MAX_VALUE;
        int[] answer = new int[2];

        for (int right = 0; right < gems.length; right++) {
            window.put(gems[right], window.getOrDefault(gems[right], 0) + 1);

            while (window.size() == totalTypes) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    answer[0] = left + 1;
                    answer[1] = right + 1;
                }

                window.put(gems[left], window.get(gems[left]) - 1);
                if (window.get(gems[left]) == 0) {
                    window.remove(gems[left]);
                }
                left++;
            }
        }

        return answer;
    }
}