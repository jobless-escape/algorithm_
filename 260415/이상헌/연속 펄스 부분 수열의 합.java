class Solution {
    public long solution(int[] sequence) {
        return Math.max(kadane(sequence, 1), kadane(sequence, -1));
    }

    private long kadane(int[] sequence, int startPulse) {
        long dp = 0;
        long answer = Long.MIN_VALUE;
        int pulse = startPulse;

        for (int val : sequence) {
            long cur = (long) val * pulse;
            dp = Math.max(cur, dp + cur);
            answer = Math.max(answer, dp);
            pulse *= -1;
        }

        return answer;
    }
}