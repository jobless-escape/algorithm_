import java.util.*;

class Solution {

    int maxDiff = 0;
    int[] answer = {-1};
    int n;
    int[] info;

    public int[] solution(int n, int[] info) {
        this.n = n;
        this.info = info;
        dfs(0, new int[11]);

        return answer;
    }

    void dfs(int idx, int[] lion) {

        int used = 0;
        for(int i = 0; i < 11; i++){
            used += lion[i];
        }

        int arrows = this.n - used;

        // 점수 계산
        if(idx == 11){
            
            if(arrows > 0)
                lion[10] += arrows;

            int lionScore = 0;
            int apeachScore = 0;

            for(int i = 0; i < 11; i++){
                if(lion[i] == 0 && info[i] == 0) continue;
                
                if(lion[i] > info[i])
                    lionScore += 10 - i;
                else
                    apeachScore += 10 - i;
            }

            int diff = lionScore - apeachScore;

            if(diff <= 0){
                if(arrows > 0) lion[10] -= arrows;
                return;
            }

            if(diff > maxDiff){
                maxDiff = diff;
                answer = lion.clone();
            }
            else if(diff == maxDiff){

                for(int i = 10; i >= 0; i--){
                    if(lion[i] > answer[i]){
                        answer = lion.clone();
                        break;
                    }
                    else if(lion[i] < answer[i])
                        break;
                }
            }

            if(arrows > 0) lion[10] -= arrows;
            return;
        } // 계산 끝

        int need = info[idx] + 1;
        if(arrows >= need){
            lion[idx] = need;
            dfs(idx + 1, lion);
            lion[idx] = 0;
        }

        dfs(idx + 1, lion);
    }
}