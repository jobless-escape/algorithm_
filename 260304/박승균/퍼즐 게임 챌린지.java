class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = 0;
        for (int d : diffs) right = Math.max(right, d);

        int answer = right;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (canFinish(diffs, times, limit, mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }

    private boolean canFinish(int[] diffs, int[] times, long limit, int level) {
        long totalTime = times[0];
        for (int i = 1; i < diffs.length; i++) {
            if (diffs[i] <= level) {
                totalTime += times[i];
            } else {
                long wrong = diffs[i] - level;
                totalTime += wrong * (times[i - 1] + times[i]) + times[i];
            }
            if (totalTime > limit) return false;
        }
        return totalTime <= limit;
    }
}