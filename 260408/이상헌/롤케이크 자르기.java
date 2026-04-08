import java.util.*;

class Solution {
    public int solution(int[] topping) {
        int n = topping.length;

        Map<Integer, Integer> leftMap = new HashMap<>();
        Map<Integer, Integer> rightMap = new HashMap<>();

        for (int t : topping) {
            rightMap.merge(t, 1, Integer::sum);
        }

        int leftKind = 0;
        int rightKind = rightMap.size();
        int answer = 0;

        for (int i = 0; i < n - 1; i++) {
            int t = topping[i];

            leftMap.merge(t, 1, Integer::sum);
            if (leftMap.get(t) == 1) leftKind++;

            rightMap.merge(t, -1, Integer::sum);
            if (rightMap.get(t) == 0) {
                rightMap.remove(t);
                rightKind--;
            }

            if (leftKind == rightKind) answer++;
        }

        return answer;
    }
}