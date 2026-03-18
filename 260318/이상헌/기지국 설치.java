import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int coverage = 2 * w + 1;

        int prev = 1;

        for (int station : stations) {
            int left = station - w;

            int empty = left - prev;

            if (empty > 0) {
                answer += (empty + coverage - 1) / coverage;
            }

            prev = station + w + 1;
        }

        int empty = n - prev + 1;
        if (empty > 0) {
            answer += (empty + coverage - 1) / coverage;
        }

        return answer;
    }
}