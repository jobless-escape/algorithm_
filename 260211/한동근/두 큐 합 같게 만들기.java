class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int n = queue1.length;
        long target = 0;
        int[] queues = new int[n * 2];
        
        for(int i = 0 ; i < n ; i++){
            queues[i] = queue1[i];
        }
        
        for(int i = n ; i < n * 2 ; i++){
            queues[i] = queue2[i - n];
        }
        
        for(long num : queues) target += num;
        target /= 2;
        
        int l = 0;
        int r = n;
        int answer = 0;
        long sum = 0;
        
        for(int i = l; i < r ; i++) sum += queues[i];
        
        while(r < 2 * n){
            if(sum < target){
                answer++;
                sum += queues[r];
                r++;
            }
            else if(sum > target){
                answer++;
                sum -= queues[l];
                l++;
            }
            else if(sum == target){
                return answer;
            }
        }
        
        return -1;
    }
}