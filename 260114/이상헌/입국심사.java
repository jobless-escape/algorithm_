class Solution {
    public long solution(int n, int[] times) {
        // 탐색 범위 지정
        long left = 1;
        long right = (long) n * times[0];
        for (int time : times) {
            right = Math.max(right, (long) n * time);
        }

        long answer = right;

        while (left <= right) {
            long mid = (left + right) / 2;

            // mid 시간 동안 처리 가능한 사람 수
            long count = 0;
            for (int time : times) {
                count += mid / time; // 각 심사관이 mid 시간동안 처리 가능한 수.
                if (count >= n) break; // 이미 인원수 초과면 조기 종료
            }

            if (count >= n) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }
}