import java.util.*;

class Solution {

    static Set<Set<String>> result = new HashSet<>();

    public int solution(String[] user_id, String[] banned_id) {
        dfs(0, user_id, banned_id, new HashSet<>());
        return result.size();
    }

    static void dfs(int idx, String[] user, String[] banned, Set<String> set){

        if(idx == banned.length){
            result.add(new HashSet<>(set));
            return;
        }

        for(String u : user){

            if(set.contains(u)) continue;

            if(match(u, banned[idx])){
                set.add(u);
                dfs(idx+1, user, banned, set);
                set.remove(u);
            }
        }
    }

    static boolean match(String user, String ban){

        if(user.length() != ban.length()) return false;

        for(int i=0;i<user.length();i++){
            if(ban.charAt(i) == '*') continue;
            if(user.charAt(i) != ban.charAt(i)) return false;
        }

        return true;
    }
}
