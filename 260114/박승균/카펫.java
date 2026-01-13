import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        boolean exists = false;
        for (String w : words) {
            if (w.equals(target)) {
                exists = true;
                break;
            }
        }
        if (!exists) return 0;

        Queue<Map<String, Integer>> queue = new LinkedList<>();
        boolean[] visited = new boolean[words.length];

        Map<String, Integer> start = new HashMap<>();
        start.put(begin, 0);
        queue.offer(start);

        while (!queue.isEmpty()) {
            Map<String, Integer> cur = queue.poll();

            String currentWord = cur.keySet().iterator().next();
            int step = cur.get(currentWord);

            if (currentWord.equals(target)) {
                return step;
            }

            for (int i = 0; i < words.length; i++) {
                if (!visited[i] && canConvert(currentWord, words[i])) {
                    visited[i] = true;

                    Map<String, Integer> next = new HashMap<>();
                    next.put(words[i], step + 1);
                    queue.offer(next);
                }
            }
        }

        return 0;
    }

    private boolean canConvert(String a, String b) {
        int diff = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) diff++;
            if (diff > 1) return false;
        }
        return diff == 1;
    }
}