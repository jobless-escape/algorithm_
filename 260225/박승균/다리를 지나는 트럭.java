import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        Queue<Integer> bridge = new LinkedList<>();
        int cnt = 0;
        for (int w : truck_weights) {
            while (true) {
                if (bridge.size() == bridge_length) { //가득 차있으면
                    cnt -= bridge.poll(); // FIFO
                }

                if (cnt + w <= weight) { //올릴 수 있을 경우
                    bridge.add(w);
                    cnt += w;
                    answer++;
                    break;
                } else { //못 올리는 경우 0 추가
                    bridge.add(0);
                    answer++;
                }
            }
        }

        answer += bridge_length;
        
        return answer;
    }
}