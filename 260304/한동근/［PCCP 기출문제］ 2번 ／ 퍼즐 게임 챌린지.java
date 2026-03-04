class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int left = 1;
        int right = 0;
        
        for(int diff : diffs){
            right = Math.max(right, diff);
        }
        
        int n = diffs.length;
        int time_cur = 0;
        
        while(left <= right){
            int time_prev = 0;
            long second = 0;
            int mid = (left + right) / 2;
            
            for(int i = 0 ; i < n ; i++){
                int diff = diffs[i];
                time_cur = times[i];
                
                if(diff <= mid) {
                    second += time_cur;
                }
                if(diff > mid) {
                    long wrongCount = diff - mid;
                    second += ((time_prev + time_cur) * wrongCount + time_cur);
                }
                
                if(second > limit) break;
                
                time_prev = time_cur;
            }
            
            if(second <= limit) {
                answer = mid;
                right = mid - 1;
            }
            else{
                left = mid + 1;
            }
        }
        return answer;
    }
}