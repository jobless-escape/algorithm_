import java.util.*;

class Solution {
    public int solution(int[] elements) {
        int n = elements.length;
        Set<Integer> set = new HashSet<>();

        int[] arr = new int[2 * n];
        for(int i = 0; i < 2 * n; i++){
            arr[i] = elements[i % n];
        }

        int[] prefix = new int[2 * n + 1];
        for(int i = 1; i <= 2 * n; i++){
            prefix[i] = prefix[i - 1] + arr[i - 1];
        }

        for(int len = 1; len <= n; len++){
            for(int start = 0; start < n; start++){
                int sum = prefix[start + len] - prefix[start];
                set.add(sum);
            }
        }

        return set.size();
    }
}