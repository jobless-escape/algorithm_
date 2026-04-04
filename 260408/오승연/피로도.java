import java.util.*;
import java.io.*;

class Solution {
    static int[] arr;
    static int n, answer;
    static boolean[] visited;
    static int k;
    static int[][] dungeons;
    
    public int solution(int k, int[][] dungeons) {
        answer = 0;
        n=dungeons.length;
        arr=new int[n];
        visited=new boolean[n];
        this.k=k;
        this.dungeons=dungeons;
        perm(0);
        
        return answer;
    }
    
    static void perm(int cnt){
        if(cnt==n){
            int pirodo=k;
            int count=0;
            for(int i:arr){
                if(dungeons[i][0]>pirodo){
                    break;
                }else{
                    pirodo-=dungeons[i][1];
                    count++;
                }
            }
            answer=Math.max(count,answer);
            return;
        }
        
        for(int i=0;i<n;i++){
            if(!visited[i]){
                visited[i]=true;
                arr[cnt]=i;
                perm(cnt+1);
                visited[i]=false;
            }
        }
    }
}
