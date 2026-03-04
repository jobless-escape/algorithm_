import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> food = new PriorityQueue<>();
        int answer = 0;
        for(int point : scoville) food.offer(point);


        while(!food.isEmpty() && food.peek() < K){
            if(food.size() == 1) return -1;
            int f1 = food.poll();
            int f2 = food.poll();
            int mix = f1 + (f2 * 2);

            food.offer(mix);
            answer++;
        }
        return answer;
    }
}