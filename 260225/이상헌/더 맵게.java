import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Long> pq = new PriorityQueue<>();

        for (int s : scoville) {
            pq.offer((long) s);
        }

        int count = 0;

        while (pq.peek() < K) {
            if (pq.size() < 2) return -1;

            long first = pq.poll();
            long second = pq.poll();

            long newScoville = first + (second * 2);
            pq.offer(newScoville);
            count++;
        }

        return count;
    }
}