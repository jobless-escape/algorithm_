class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 100_000;
        int answer = right;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canClear(diffs, times, limit, mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }

    private boolean canClear(int[] diffs, int[] times, long limit, int level) {
        long time = 0;

        for (int i = 0; i < diffs.length; i++) {
            int diff = diffs[i];

            if (diff <= level) {
                time += times[i];
            } else {
                int retry = diff - level;
                if (i == 0) {
                    time += (long) retry * times[i] + times[i];
                } else {
                    time += (long) retry * (times[i - 1] + times[i]) + times[i];
                }
            }
            if (time > limit) return false;
        }

        return true;
    }
}