import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = new int[2];
        Arrays.fill(answer, 0);
        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
        
        for(int i = 0 ; i < operations.length ; i++){
            String[] op = operations[i].split(" ");
            int num = Integer.parseInt(op[1]);
            
            if(op[0].equals("I")){
                minPq.offer(num);
                maxPq.offer(num);
            }
            
            if(op[0].equals("D")){
                if(num == 1 && maxPq.size() > 0){
                    int removeNum = maxPq.poll();
                    minPq.remove(removeNum);
                }
                else if(num == -1 && minPq.size() > 0){
                    int removeNum = minPq.poll();
                    maxPq.remove(removeNum);
                }
            }
        }
        
        if(maxPq.size() > 0){
            answer[0] = maxPq.peek();
        }
        
        if(minPq.size() > 0){
            answer[1] = minPq.peek();
        }
        return answer;
    }
}