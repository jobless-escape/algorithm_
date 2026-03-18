import java.util.*;

class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int pos = 1;
        int stationScope = w * 2 + 1;
        
        for(int station : stations){
            int left = station - w;
            
            if(pos < left){
                double size = left - pos;
                answer += (size + stationScope - 1) / stationScope;
            }
            pos = station + w + 1;
        }
        
        if(pos <= n) {
            double size = n - pos + 1;
            answer += (size + stationScope - 1) / stationScope;
        }

        return answer;
    }
}