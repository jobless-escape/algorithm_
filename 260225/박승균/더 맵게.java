import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) {
            pq.add(s);
        }
        
        // 섞기
        while (pq.size() > 1 && pq.peek() < K) {
            int first = pq.poll();
            int second = pq.poll();
            
            int mixed = first + (second * 2);
            pq.add(mixed);
            answer++;
        }
        
        // 다했는데 최솟값이 K보다 작다면
        if (pq.peek() < K) return -1;
        
        return answer;
    }
}