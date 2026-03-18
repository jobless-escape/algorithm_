import java.util.*;

class Solution {
    public int[] solution(int n, int[] info) {
        int[] best = null;
        int maxDiff = 0;

        for (int mask = 0; mask < (1 << 11); mask++) {
            int[] ryan = new int[11];
            int arrows = 0;
            int ryanScore = 0, apeachScore = 0;

            for (int i = 0; i <= 10; i++) {
                int score = 10 - i;
                if ((mask & (1 << i)) != 0) {
                    ryan[i] = info[i] + 1;
                    arrows += ryan[i];
                    ryanScore += score;
                } else {
                    if (info[i] > 0) apeachScore += score;
                }
            }

            if (arrows > n) continue;

            ryan[10] += (n - arrows);

            int diff = ryanScore - apeachScore;

            if (diff > maxDiff) {
                maxDiff = diff;
                best = ryan.clone();
            } else if (diff == maxDiff && diff > 0) {
                if (isLowerPreferred(ryan, best)) {
                    best = ryan.clone();
                }
            }
        }

        return best == null ? new int[]{-1} : best;
    }

    private boolean isLowerPreferred(int[] ryan, int[] best) {
        for (int i = 10; i >= 0; i--) {
            if (ryan[i] != best[i]) {
                return ryan[i] > best[i];
            }
        }
        return false;
    }
}