import java.util.*;

class Solution {
    char[] vowels = {'A', 'E', 'I', 'O', 'U'};
    List<String> dict = new ArrayList<>();

    public int solution(String word) {
        dfs("");
        return dict.indexOf(word) + 1;
    }

    void dfs(String current) {
        if (current.length() == 5) return;

        for (char v : vowels) {
            String next = current + v;
            dict.add(next);
            dfs(next);
        }
    }
}