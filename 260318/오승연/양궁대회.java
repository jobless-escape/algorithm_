import java.util.*;

class Solution {
    static int[] res=new int[11];
    static int[] lion={-1}; //정답배열
    static int max=0;
    
    public int[] solution(int n, int[] info) {
        dfs(0,n,info);
        
        if(max==-1){
            lion = new int[1];
        	lion[0]=-1;
        }
        return lion;
    }
    
    static void dfs(int depth, int n, int[] info){
        // depth = 과녁 인덱스
        if(depth == 10){
            res[depth] = n; // 남은 화살 몰빵 (왜냐? 가장 큰 점수차로 이겨야하니까)
            
            int diff = score(info);
            if(diff > max){
                max = diff;
                lion = res.clone();
            }else if(diff == max){
                if(isBetter()){
                    lion = res.clone();
                }
            }
            
            res[depth] = 0; // 복구
            return;
        }
        
        // 1. 해당 과녁 포기
        res[depth] = 0;
        dfs(depth+1, n, info);
        
        // 2. 해당 과녁 이기기
        int need = info[depth] + 1;
        if(n >= need){
            res[depth] = need;
            dfs(depth+1, n - need, info);
            res[depth] = 0;
        }
    }
    
    // 점수차 같으면, 낮은 점수 더 많이 맞힌 배열 선택
    static boolean isBetter(){
        for(int i=10; i>=0; i--){
            if(res[i] > lion[i]) return true;
            else if(res[i] < lion[i]) return false;
        }
        return false;
    }
    
    // 점수차 구하는 함수
    static int score(int[] info){
        int apeach=0, lion=0;
    	for(int i=0; i<res.length; i++) {
    		if(info[i]==0 && res[i]==0) continue;
            
    		if(info[i]>=res[i]) apeach += (10-i);
    		else lion += (10-i);
    	}
    	
    	int diff = lion - apeach;
    	if(diff<=0) return -1;
    	return diff;
    }
}
