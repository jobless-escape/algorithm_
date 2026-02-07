import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;
        Queue<Integer> q1=new LinkedList<>();
        Queue<Integer> q2=new LinkedList<>();
        long sum1=0;
        long sum2=0;
        for(int i:queue1) sum1+=i;
        for(int i:queue2) sum2+=i;
        long total=sum1+sum2;
        int n=queue1.length+queue2.length;
        for(int q:queue1) q1.offer(q);
        for(int q:queue2) q2.offer(q);
        int limit=n*3;
        
        while(answer<=limit){
            if(sum1==total/2) return answer;
            else if(sum1>total/2){
                int x=q1.poll();
                sum1-=x;
                sum2+=x;
                q2.offer(x);
            }else{
                int x=q2.poll();
                sum1+=x;
                sum2-=x;
                q1.offer(x);
            }
            
            answer++;
        }
        
        return -1;
    }
}
