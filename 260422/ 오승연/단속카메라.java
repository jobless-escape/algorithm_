import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = Integer.MIN_VALUE;
        int cnt=0;
        
        Arrays.sort(routes, (a,b)-> Integer.compare(a[1],b[1]));
        
        for(int[] route:routes){
            if(answer<route[0]){
                answer=route[1];
                cnt++;
            }
        }
        return cnt;
    }
}
