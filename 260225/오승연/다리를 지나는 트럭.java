import java.io.*;
import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {        
        Queue<Integer> wait = new LinkedList<>();
        for(int i : truck_weights){
            wait.offer(i);
        }
        
        Queue<Integer> bridge = new LinkedList<>();
        int sum_weight = 0;
        int time = 0;
        
        // 다리를 0으로 초기화
        for(int i = 0; i < bridge_length; i++){
            bridge.offer(0);
        }
        
        while(!wait.isEmpty() || sum_weight > 0){
            time++;
            
            // 다리에서 트럭 하나 제거 (맨 앞)
            int out = bridge.poll();
            sum_weight -= out;
            
            // 대기 중인 트럭이 있고, 다리에 올릴 수 있다면
            if(!wait.isEmpty() && sum_weight + wait.peek() <= weight){
                int next = wait.poll();
                bridge.offer(next);
                sum_weight += next;
            } else {
                // 트럭을 올릴 수 없다면 0을 추가 (빈 공간)
                bridge.offer(0);
            }
        }
        
        return time;
    }
}
