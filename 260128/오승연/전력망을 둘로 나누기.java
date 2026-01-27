import java.io.*;
import java.util.*;

class Solution {
    static boolean[] visited;
    static List<Integer>[] list;
    static int count;
    static int min;
    
    public int solution(int n, int[][] wires) {
        list=new ArrayList[n+1];
        for(int i=0;i<n+1;i++){
            list[i]=new ArrayList<>();
        }
        for(int i=0;i<wires.length;i++){
            list[wires[i][0]].add(wires[i][1]);
            list[wires[i][1]].add(wires[i][0]);
        }
        min=101;
        
        for(int i=0;i<wires.length;i++){
            visited=new boolean[n+1];
            count=0;
            
            int cutA = wires[i][0];
            int cutB = wires[i][1];

            visited[cutB] = true; // 전선 끊는 효과
            dfs(cutA);           
            
            min=Math.min(Math.abs(count-(n-count)),min);
        }
        return min;
    }
    
    static void dfs(int idx){
        visited[idx]=true;
        count++;
        
        for(int i:list[idx]){
            if(!visited[i]){
                dfs(i);
            }
        }
    }
}
