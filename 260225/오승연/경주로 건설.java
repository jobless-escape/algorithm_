import java.io.*;
import java.util.*;

class Solution {
    static int n, answer;
    static int max=Integer.MAX_VALUE;
    static int[][][] visited;
    static int[] dx={0,0,1,-1};
    static int[] dy={1,-1,0,0};
    
    static class Node{
        int r,c,cost,dir;
        
        Node(int r,int c,int cost,int dir){
            this.r=r;
            this.c=c;
            this.cost=cost;
            this.dir=dir;
        }
    }
    
    public int solution(int[][] board) {
        answer = max;
        n=board.length;
        visited=new int[n][n][4];
        
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                for(int k=0;k<4;k++){
                    visited[i][j][k]=max;
                }
            }
        }
        
        bfs(board);
        return answer;
    }
    
    static void bfs(int[][] board){
        Queue<Node> q=new LinkedList<>();
        q.offer(new Node(0,0,0,-1)); //처음 방향만 -1로 설정
        
        while(!q.isEmpty()){
            Node cur=q.poll();
            int x=cur.r;
            int y=cur.c;
            int c=cur.cost;
            int d=cur.dir;
            
            if(x==n-1 && y==n-1){
                answer=Math.min(answer,c);
            }
            
            for(int i=0;i<4;i++){
                int nx=x+dx[i];
                int ny=y+dy[i];
                
                if(isEdge(nx,ny) || board[nx][ny]==1) continue;
                
                int cost=c;
                if(d==-1 || d==i){
                    cost+=100;
                }else{
                    cost+=600;
                }
                
                if(visited[nx][ny][i]>=cost){
                    visited[nx][ny][i]=cost;
                    q.offer(new Node(nx,ny,cost,i));
                }
            }
        }
    }
    
    static boolean isEdge(int x,int y){
        return x<0||y<0||x>=n||y>=n;
    }
}
