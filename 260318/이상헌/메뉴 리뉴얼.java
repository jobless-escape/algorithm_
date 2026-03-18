import java.util.*;

class Solution {
    private Map<String, Integer> counter = new HashMap<>();

    public String[] solution(String[] orders, int[] course) {
        List<String> result = new ArrayList<>();

        for (int size : course) {
            counter.clear();

            for (String order : orders) {
                char[] chars = order.toCharArray();
                Arrays.sort(chars);
                String sorted = new String(chars);

                combine(sorted, 0, size, new StringBuilder());
            }

            int maxCount = counter.values().stream()
                    .mapToInt(Integer::intValue)
                    .max()
                    .orElse(0);

            if (maxCount >= 2) {
                for (Map.Entry<String, Integer> entry : counter.entrySet()) {
                    if (entry.getValue() == maxCount) {
                        result.add(entry.getKey());
                    }
                }
            }
        }

        Collections.sort(result);
        return result.toArray(new String[0]);
    }

    private void combine(String str, int start, int size, StringBuilder current) {
        if (current.length() == size) {
            counter.merge(current.toString(), 1, Integer::sum);
            return;
        }

        for (int i = start; i < str.length(); i++) {
            current.append(str.charAt(i));
            combine(str, i + 1, size, current);
            current.deleteCharAt(current.length() - 1);
        }
    }
}