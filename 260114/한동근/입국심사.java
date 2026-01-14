import java.util.*;

class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);
        
        long left = 1;
        long right = times[times.length -1] * (long)n;
        
        while(left <= right){
            long mid = (left + right) / 2;
            long totalPeople = 0;
            
            for(int time : times){
                totalPeople += (mid / time);
            }
            
            if(totalPeople < n){
                left = mid + 1;
            }
            else if(totalPeople >= n){
                right = mid - 1;
                answer = mid;
            }
        }
        return answer;
    }
}