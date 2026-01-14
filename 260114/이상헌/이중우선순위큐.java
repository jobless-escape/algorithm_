import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        // 최소힙
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
        // 최대힙

        for(String operation : operations) {
            String[] parts = operation.split(" ");
            String cmd = parts[0];
            int num = Integer.parseInt(parts[1]);

            if(cmd.equals("I")) {
                minPq.offer(num);
                maxPq.offer(num);
            } else if(cmd.equals("D")) {
                if(minPq.isEmpty()) continue;

                if(num == 1) { // 최댓값 삭제
                    int max = maxPq.poll();
                    minPq.remove(max);
                } else { // 최솟값 삭제
                    int min = minPq.poll();
                    maxPq.remove(min);
                }
            }
        }

        if(minPq.isEmpty()) {
            return new int[]{0, 0};
        }

        int[] answer = {maxPq.poll(), minPq.poll()};
        return answer;
    }
}