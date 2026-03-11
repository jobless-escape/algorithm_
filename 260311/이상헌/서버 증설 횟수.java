class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int[] boost = new int[24];

        for (int i = 0; i < 24; i++) {
            int need = players[i] / m;

            if (need == 0) continue;

            int active = 0;
            for (int j = Math.max(0, i - k + 1); j < i; j++) {
                active += boost[j];
            }

            if (need > active) {
                int add = need - active;
                boost[i] = add;
                answer += add;
            }
        }

        return answer;
    }
}