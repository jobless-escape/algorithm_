import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        Queue<Integer> minQueue=new PriorityQueue<>();
        Queue<Integer> maxQueue=new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i=0;i<operations.length;i++){
            String[] op=operations[i].split(" ");
            if(op[0].equals("I")){
                minQueue.offer(Integer.valueOf(op[1]));
                maxQueue.offer(Integer.valueOf(op[1]));
            }
            if(! minQueue.isEmpty()){
                if(operations[i].equals("D -1")){
                    maxQueue.remove(minQueue.poll());
                }else if(operations[i].equals("D 1")){
                    minQueue.remove(maxQueue.poll());
                }
            }
        }
        if(minQueue.isEmpty()){
            answer[0]=0;
            answer[1]=0;
        }else{
            answer[0]=maxQueue.poll();
            answer[1]=minQueue.poll();
        }
        return answer;
    }
}
