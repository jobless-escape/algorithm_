import java.util.*;
import java.util.function.Function;

class Solution {
    public int solution(int n, Function<Integer, String> submit) {
        // 가능한 후보 생성
        List<Integer> candidates = new ArrayList<>();
        for (int a = 1; a <= 9; a++) {
            for (int b = 1; b <= 9; b++) {
                if (b == a) continue;
                for (int c = 1; c <= 9; c++) {
                    if (c == a || c == b) continue;
                    for (int d = 1; d <= 9; d++) {
                        if (d == a || d == b || d == c) continue;
                        int num = a*1000 + b*100 + c*10 + d;
                        candidates.add(num);
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if (candidates.isEmpty()) break;

            // 임의선택
            int guess = candidates.get(candidates.size() - 1);
            String result = submit.apply(guess);

            if (result.equals("4S 0B")) {
                return guess;
            }

            final String res = result;
            // 후보 필터링
            List<Integer> newList = new ArrayList<>();
            for (int cand : candidates) {
                if (getScore(cand, guess).equals(res)) {
                    newList.add(cand);
                }
            }
            candidates = newList;
        }

        // 후보가 하나만 남으면 반환
        return candidates.isEmpty() ? 0 : candidates.get(0);
    }

    private static String getScore(int secret, int guess) {
        String s = String.valueOf(secret);
        String g = String.valueOf(guess);
        int strike = 0;
        int ball = 0;

        for (int i = 0; i < 4; i++) {
            if (s.charAt(i) == g.charAt(i)) {
                strike++;
            } else if (s.contains(String.valueOf(g.charAt(i)))) {
                ball++;
            }
        }
        return strike + "S " + ball + "B";
    }
}