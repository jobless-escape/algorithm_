import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        
        for(int i:scoville){
            pq.offer(i);
        }
        
        while(true){
            if(pq.peek()>=K || pq.size()==1) break;
            int res=pq.poll()+(pq.poll()*2);
            pq.offer(res);
            answer++;
        }
        
        if(pq.peek()<K || pq.peek()==0){
            answer=-1;
        }
        
        return answer;
    }
}
