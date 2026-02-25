import java.util.Arrays;

class Solution {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        int answer = 0;
        int aPointer = 0;

        for (int i = 0; i < B.length; i++) {
            if (B[i] > A[aPointer]) {
                answer++;
                aPointer++; // A의 이긴 값 소모
            }
        }

        return answer;
    }
}