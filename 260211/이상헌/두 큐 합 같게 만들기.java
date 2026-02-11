import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length;
        int m = 2 * n;
        long[] arr = new long[m];

        long sum1 = 0, total = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = queue1[i];
            sum1 += arr[i];
            total += arr[i];
        }
        for (int i = 0; i < n; i++) {
            arr[n + i] = queue2[i];
            total += arr[n + i];
        }

        if (total % 2 != 0) return -1;
        long target = total / 2;

        for (long v : arr) {
            if (v > target) return -1;
        }

        int left = 0;
        int right = n;
        int cnt = 0;

        int limit = 4 * n;

        while (cnt <= limit) {
            if (sum1 == target) return cnt;

            if (sum1 > target) {
                sum1 -= arr[left % m];
                left++;
            } else {
                sum1 += arr[right % m];
                right++;
            }
            cnt++;
        }

        return -1;
    }
}
