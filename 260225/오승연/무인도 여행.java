import java.io.*;
import java.util.*;

class Solution {
    static boolean[][] visited;
    static int n,m,sum;
    static int[] dx={0,0,1,-1};
    static int[] dy={1,-1,0,0};
    static int[][] map;
    
    public List<Integer> solution(String[] maps) {
        n=maps.length;
        m=maps[0].length();
        List<Integer> answer = new ArrayList<>();
        map=new int[n][m];
        
        String[][] temp=new String[n][m];
        for(int i=0;i<n;i++){
            String[] st=maps[i].split("");
            for(int j=0;j<m;j++){
                temp[i][j]=st[j];
            }
        }
        
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(temp[i][j].equals("X")){
                    map[i][j]=0;
                }else{
                    map[i][j]=Integer.parseInt(temp[i][j]);
                }
            }
        }
        
        visited=new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j] && map[i][j]>0){
                    sum=0;
                    bfs(i,j);
                    answer.add(sum);
                }
            }
        }
        
        Collections.sort(answer);
        if(answer.size()==0) answer.add(-1);
        return answer;
    }
    
    static void bfs(int x,int y){
        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{x,y});
        visited[x][y]=true;
        sum+=map[x][y];
        
        while(!q.isEmpty()){
            int[] cur=q.poll();
            int cx=cur[0];
            int cy=cur[1];
            
            for(int i=0;i<4;i++){
                int nx=cx+dx[i];
                int ny=cy+dy[i];
                
                if(isEdge(nx,ny) || visited[nx][ny] || map[nx][ny]==0) continue;
                
                visited[nx][ny]=true;
                sum+=map[nx][ny];
                q.offer(new int[]{nx,ny});
            }
        }
    }
    
    static boolean isEdge(int x,int y){
        return x<0||y<0||x>=n||y>=m;
    }
}
