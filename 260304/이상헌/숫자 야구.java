import java.util.*;
import java.util.function.Function;

class Solution {

    List<Integer> allList = new ArrayList<>();

    public int solution(int n, Function<Integer, String> submit) {
        List<Integer> candidates = new ArrayList<>();
        for (int i = 1234; i <= 9876; i++) {
            int[] d = digits(i);
            if (hasZero(d) || hasDup(d)) continue;
            candidates.add(i);
            allList.add(i);
        }

        while (!candidates.isEmpty()) {
            int guess;
            if (candidates.size() == 1) {
                guess = candidates.get(0);
            } else {
                guess = bestGuess(candidates, allList);
            }

            String result = submit.apply(guess);
            if (result.equals("4S 0B")) return guess;

            int s = result.charAt(0) - '0';
            int b = result.charAt(3) - '0';

            List<Integer> filtered = new ArrayList<>();
            for (int c : candidates) {
                if (calcStrike(guess, c) == s && calcBall(guess, c) == b) {
                    filtered.add(c);
                }
            }
            candidates = filtered;
        }
        return -1;
    }

    private int bestGuess(List<Integer> candidates, List<Integer> pool) {
        int bestGuess = candidates.get(0);
        int bestWorstCase = Integer.MAX_VALUE;
        boolean bestIsCandidate = false;

        for (int guess : pool) {
            Map<Integer, Integer> buckets = new HashMap<>();
            for (int c : candidates) {
                int key = calcStrike(guess, c) * 10 + calcBall(guess, c);
                buckets.merge(key, 1, Integer::sum);
            }
            int worstCase = Collections.max(buckets.values());
            boolean isCandidate = candidates.contains(guess);

            if (worstCase < bestWorstCase ||
                    (worstCase == bestWorstCase && isCandidate && !bestIsCandidate)) {
                bestWorstCase = worstCase;
                bestGuess = guess;
                bestIsCandidate = isCandidate;
            }
        }
        return bestGuess;
    }

    private int[] digits(int n) {
        return new int[]{n/1000, (n/100)%10, (n/10)%10, n%10};
    }

    private boolean hasZero(int[] d) {
        for (int x : d) if (x == 0) return true;
        return false;
    }

    private boolean hasDup(int[] d) {
        Set<Integer> set = new HashSet<>();
        for (int x : d) if (!set.add(x)) return true;
        return false;
    }

    private int calcStrike(int g, int a) {
        int[] gd = digits(g), ad = digits(a);
        int s = 0;
        for (int i = 0; i < 4; i++) if (gd[i] == ad[i]) s++;
        return s;
    }

    private int calcBall(int g, int a) {
        int[] gd = digits(g), ad = digits(a);
        int b = 0;
        for (int i = 0; i < 4; i++) {
            if (gd[i] != ad[i]) {
                for (int j = 0; j < 4; j++) {
                    if (i != j && gd[i] == ad[j]) { b++; break; }
                }
            }
        }
        return b;
    }
}