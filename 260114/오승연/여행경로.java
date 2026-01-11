import java.io.*;
import java.util.*;

class Solution {
    static String[] answer;
    static int len;
    static boolean[] visited;
    static int index;
    static boolean finish;
    
    public String[] solution(String[][] tickets) {
        len=tickets.length;
        answer=new String[len+1];
        visited=new boolean[len];
        index=0;
        finish=false;
        
        // 이차원배열 정렬
        Arrays.sort(tickets, (a, b) -> {
            if (a[0].equals(b[0])) {
                return a[1].compareTo(b[1]); 
            }
            return a[0].compareTo(b[0]); 
        });

        // System.out.println(Arrays.deepToString(tickets));
        for(int i=0;i<len;i++){
            if(tickets[i][0].equals("ICN")){
                visited[i]=true;
                dfs(i, tickets);
                if(finish) break;     // 정답 찾으면 종료
                visited[i]=false;    // 되돌리기
                index=0;             // 초기화
            }
        }
        
        return answer;
    }
    
    static void dfs(int idx, String[][] tickets){
        
        answer[index]=tickets[idx][0];
        index++;
        
        // 모든 티켓 사용 시 마지막 공항 추가
        if(index==len){
            answer[index]=tickets[idx][1];
            finish=true;
            return;
        }
        
        String two= tickets[idx][1];
        
        for(int i=0;i<len;i++){
            if(!visited[i] && tickets[i][0].equals(two)){
                visited[i]=true;
                dfs(i, tickets);
                if(finish) return; // 종료
                visited[i]=false; // 되돌리기
                index--;          // 인덱스 복구
            }
        }
    }
}
