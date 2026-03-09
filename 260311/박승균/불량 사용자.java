import java.util.*;

class Solution {

    Set<String> set = new HashSet<>();
    boolean[] visited;
    String[] user_id;
    String[] banned_id;
    
    public int solution(String[] user_id, String[] banned_id) {
        this.user_id = user_id;
        this.banned_id = banned_id;
        visited = new boolean[user_id.length];
        
        dfs(0);
        
        return set.size();
    }

    void dfs(int depth) {
        if (depth == banned_id.length) {

            List<String> list = new ArrayList<>();

            for (int i = 0; i < user_id.length; i++) {
                if (visited[i]) list.add(user_id[i]);
            }

            Collections.sort(list);
            set.add(String.join(",", list));
            return;
        }

        for (int i = 0; i < user_id.length; i++) {

            if (visited[i]) continue;

            if (match(user_id[i], banned_id[depth])) {
                visited[i] = true;
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }

    boolean match(String user, String banned) {

        if (user.length() != banned.length()) return false;

        for (int i = 0; i < user.length(); i++) {
            if (banned.charAt(i) == '*') continue;
            if (user.charAt(i) != banned.charAt(i)) return false;
        }

        return true;
    }
}