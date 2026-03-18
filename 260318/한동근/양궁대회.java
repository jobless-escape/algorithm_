import java.util.*;

class Solution {
    static int[] answer;
    static int[] lion;
    static int maxDiff = Integer.MIN_VALUE;
    public int[] solution(int n, int[] info) {
        answer = new int[11];
        lion = new int[11];
        Arrays.fill(lion, 0);
        
        dfs(0, n, info);
        
        if(maxDiff == Integer.MIN_VALUE){
            return new int[]{-1};
        }
        
        return answer;
    }
    
    static void dfs(int index, int n, int[] info){
        if(index == 11){
            if(n > 0) lion[10] += n;
            int lionScore = 0;
            int apeachScore = 0;
            
            for(int i = 0 ; i < 11 ; i++){
                if(lion[i] == 0 && info[i] == 0) continue;
                else if(info[i] >= lion[i]) apeachScore += (10 - i);
                else if(lion[i] > info[i]) lionScore += (10 - i);
            }
            
            if(lionScore > apeachScore){
                int diff = lionScore - apeachScore;
                if(diff > maxDiff){
                    maxDiff = diff;
                    answer = lion.clone();
                }
                else if(diff == maxDiff){
                    for(int i = 10 ; i >= 0 ; i--){
                        if(answer[i] < lion[i]){
                            answer = lion.clone();
                            break;
                        }
                        else if(answer[i] > lion[i]) break;
                    }
                }
            }
            
            if(n > 0) lion[10] -= n;
            return;
        }
        
        if(n > info[index]){
            int arrow = info[index] + 1;
            lion[index] = arrow;
            dfs(index + 1, n - arrow, info);
            lion[index] = 0;
        }
        
        dfs(index + 1, n, info);
    }
}