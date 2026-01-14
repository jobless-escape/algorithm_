import java.util.*;

class Solution {
    class Pair {
        String word;
        int step;
        Pair(String word, int step) {
            this.word = word;
            this.step = step;
        }
    }
    public int solution(String begin, String target, String[] words) {
        //
        boolean hasTarget = false;
        for (String word : words) {
            if (word.equals(target)) {
                hasTarget = true;
                break;
            }
        }
        if (!hasTarget) return 0;

        // 2. 방문 체크용 boolean 배열
        boolean[] visited = new boolean[words.length];

        // 3. BFS 시작
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(begin, 0));

        while (!queue.isEmpty()) {
            Pair current = queue.poll();
            String word = current.word;
            int step = current.step;

            // 4. 목표 도달
            if (word.equals(target)) {
                return step;
            }

            // 5. words 배열 전체 순회
            for (int i = 0; i < words.length; i++) {
                // 이미 방문했으면 스킵
                if (visited[i]) continue;

                // 한 글자만 다른지 확인
                if (isOneLetterDiff(word, words[i])) {
                    visited[i] = true;  // 방문 체크
                    queue.offer(new Pair(words[i], step + 1));
                }
            }
        }

        return 0;
    }

    // 두 단어가 정확히 한 글자만 다른지 확인
    private boolean isOneLetterDiff(String word1, String word2) {
        if (word1.length() != word2.length()) return false;

        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCount++;
                if (diffCount > 1) return false;  // 2개 이상 다르면 false
            }
        }
        return diffCount == 1;  // 정확히 1개만 달라야 true
    }


}