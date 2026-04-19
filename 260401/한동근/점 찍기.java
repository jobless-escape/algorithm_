import java.util.*;

class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        
        for(int x = 0 ; x <= d ; x += k){
            // 피타고라스 정리 사용해서 Y 최대길이 구함
            long maxY = (long)Math.sqrt(((long)d * d - (long)x * x));
            // Y 좌표는 +k 만큼 계속 증가하는 거니까 k를 나눠준다.
            // 그리고 각 x 좌표에 대한 y가 0인 조합도 넣어줘야 함. 그래서 + 1
            answer += (maxY / k) + 1;
        }
        return answer;
    }
}