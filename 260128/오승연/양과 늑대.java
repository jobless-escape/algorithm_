import java.util.*;
import java.io.*;

class Solution {
    static Set<Integer> visited;
    static List<Integer>[] adj;
    static int maxSheep;
    static int[] info;
    
    public int solution(int[] info, int[][] edges) {
        maxSheep=0;
        int n=info.length;
        visited=new HashSet<>();
        adj=new ArrayList[n];
        for(int i=0;i<n;i++){
            adj[i]=new ArrayList<>();
        }
        this.info=info;
        
        for(int[] edge:edges){
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        visited.add(0);
        dfs(0,1,0,visited);
        return maxSheep;
    }
    
    static void dfs(int idx, int sheep, int wolf, Set<Integer> visited){
        if(wolf>=sheep){
            return;
        }
        
        maxSheep=Math.max(sheep,maxSheep);
        
        for(int i:visited){
            for(int j:adj[i]){
                if(!visited.contains(j)){
                    Set<Integer> visited2=new HashSet<>(visited);
                    visited2.add(j);
                    
                    if(info[j]==0){
                        dfs(idx+1, sheep+1, wolf, visited2);    
                    }else{
                        dfs(idx+1, sheep, wolf+1, visited2);
                    }
                }
            }
        }
    }
}
