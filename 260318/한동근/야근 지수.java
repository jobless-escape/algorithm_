import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int work : works) pq.offer(work);
        
        while(!pq.isEmpty() && n > 0){
            int now = pq.poll();
            now--; n--;
            if(now > 0) pq.offer(now);
        }
        
        long answer = 0;
        for(int stress : pq){
            answer += Math.pow(stress, 2);
        }
        return answer;
    }
}
