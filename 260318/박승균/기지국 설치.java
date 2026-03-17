class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int range = 2 * w + 1;
        int cursor = 1;

        for (int station : stations) {
            int left = station - w;

            if (cursor < left) {
                int len = left - cursor;
                answer += (len + range - 1) / range;
            }

            cursor = station + w + 1;
        }

        if (cursor <= n) {
            int len = n - cursor + 1;
            answer += (len + range - 1) / range;
        }

        return answer;
    }
}