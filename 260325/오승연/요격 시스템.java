import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, (a,b)->Integer.compare(a[1],b[1]));
        int curEnd=-1;
        
        for(int[] target:targets){
            if(curEnd==-1){ //처음일때
                answer++;
                curEnd=target[1];
                continue;
            }
            
            if(target[0]<curEnd){
                continue;
            }
            
            answer++;
            curEnd=target[1];
        }
        return answer;
    }
}
