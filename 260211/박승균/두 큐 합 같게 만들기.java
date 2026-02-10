import java.util.*;

class Solution {
    public long solution(int[] queue1, int[] queue2) {
        int answer = -1;
        Queue<Long> q1 = new LinkedList<>();
        Queue<Long> q2 = new LinkedList<>();
        long cnt = 0;
        for(int i = 0; i < queue1.length; i++){
            long a = queue1[i]; long b = queue2[i];
            cnt += a + b;
            q1.add(a);
            q2.add(b);
        }
        if (cnt % 2 != 0) return -1;
        long treshold = cnt / 2;
        cnt = 0;
        long tr = -1;
        long n = q1.size();
        long sum = calc(q1);
        
        while(tr < n * 3){
            tr ++;
            if(sum == treshold) return tr;
            else if(sum > treshold){
                long tmp = q1.poll();
                q2.add(tmp);
                sum -= tmp;
            }
            else {
                long tmp = q2.poll();
                q1.add(tmp);
                sum += tmp;
            }
        }
        
        return answer;
    }
    
    static long calc(Queue<Long> q){
        long count = 0;
        for(long n : q){
            count += n;
        }
        return count;
    }
}