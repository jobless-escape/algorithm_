import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        Arrays.sort(B);
        Arrays.sort(A);
        int idx = A.length - 1;
        int idx2 = A.length - 1;
        
        for(int i = idx2; i >= 0 ; i--){
            if(A[i] < B[idx2]){
                answer++;
                idx2--;
            }
        }
        return answer;
    }
}