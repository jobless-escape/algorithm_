import java.util.*;
import java.util.function.Function;

class Solution {
    
    private String score(int secret, int guess) {
        int strike = 0, ball = 0;
        String s = String.valueOf(secret);
        String g = String.valueOf(guess);
        
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (s.charAt(i) == g.charAt(j)) {
                    if (i == j) strike++;
                    else ball++;
                }
            }
        }
        return strike + "S " + ball + "B";
    }
    
    private List<Integer> makeCandidates() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1000; i <= 9999; i++) {
            String s = String.valueOf(i);
            boolean valid = true;
            
            // 0 포함 여부 체크
            for (char c : s.toCharArray()) {
                if (c == '0') { valid = false; break; }
            }
            if (!valid) continue;
            
            // 중복 숫자 체크
            Set<Character> set = new HashSet<>();
            for (char c : s.toCharArray()) set.add(c);
            if (set.size() == 4) list.add(i);
        }
        return list;
    }
    
    public int solution(int n, Function<Integer, String> submit) {
        List<Integer> candidates = makeCandidates();
        
        for (int i = 0; i < n; i++) {
            int guess = candidates.get(0);
            String hint = submit.apply(guess);
            
            if (hint.equals("4S 0B")) return guess;
            
            List<Integer> next = new ArrayList<>();
            for (int c : candidates) {
                if (score(c, guess).equals(hint)) next.add(c);
            }
            candidates = next;
        }
        
        return candidates.get(0);
    }
}