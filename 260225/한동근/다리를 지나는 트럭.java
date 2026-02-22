import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 1;
        Queue<Integer> wait = new ArrayDeque<>();
        Queue<Integer> line = new ArrayDeque<>();
        Queue<Integer> finish = new ArrayDeque<>();
        
        int nowWeight = 0;
        
        for(int truck : truck_weights) wait.offer(truck);
        
        while(finish.size() != truck_weights.length){
            answer++;
            
            if(line.size() < bridge_length){
                if(!wait.isEmpty() && nowWeight + wait.peek() <= weight) {
                    nowWeight += wait.peek();
                    line.offer(wait.poll());
                }
                else line.offer(0);
                
            }
            
            if(line.size() == bridge_length){
                int now = line.poll();
                if(now > 0) {
                    finish.offer(now);
                    nowWeight -= now;
                }
                
            }
        }
        return answer;
    }
}