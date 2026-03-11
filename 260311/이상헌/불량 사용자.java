import java.util.*;

class Solution {
    Set<Set<Integer>> result = new HashSet<>();
    List<List<Integer>> candidates;

    public int solution(String[] userId, String[] bannedId) {
        candidates = new ArrayList<>();

        for (String banned : bannedId) {
            List<Integer> matched = new ArrayList<>();
            String regex = banned.replace("*", ".");
            for (int i = 0; i < userId.length; i++) {
                if (userId[i].matches(regex)) {
                    matched.add(i);
                }
            }
            candidates.add(matched);
        }

        backtrack(0, new HashSet<>());

        return result.size();
    }

    void backtrack(int depth, Set<Integer> selected) {
        if (depth == candidates.size()) {
            result.add(new HashSet<>(selected));
            return;
        }

        for (int userId : candidates.get(depth)) {
            if (!selected.contains(userId)) {
                selected.add(userId);
                backtrack(depth + 1, selected);
                selected.remove(userId);
            }
        }
    }
}