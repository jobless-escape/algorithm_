import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(A);
        Arrays.sort(B);
        int aidx = 0;
        int bidx = 0;
        
        while(bidx < B.length && aidx < A.length){
            if(B[bidx] > A[aidx]){
                bidx++;
                aidx++;
                answer++;
            }
            else{
                bidx++;
            }
        }
        
        return answer;
    }
}