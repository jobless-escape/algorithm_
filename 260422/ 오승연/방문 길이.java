import java.util.*;

class Solution {
    static boolean[][][][] visited;
    
    public int solution(String dirs) {
        int answer = 0;
        visited=new boolean[11][11][11][11];
        int cx=5;
        int cy=5;
        
        String[] d=dirs.split("");
        for(int i=0;i<d.length;i++){
            int nx=cx;
            int ny=cy;
            
            if(d[i].equals("U")){
                nx-=1;
            }else if(d[i].equals("D")){
                nx+=1;
            }else if(d[i].equals("L")){
                ny-=1;
            }else{ //R
                ny+=1;
            }
            
            if(isEdge(nx,ny)) continue;
            
            if(!visited[cx][cy][nx][ny]){
                visited[cx][cy][nx][ny]=true;
                visited[nx][ny][cx][cy] = true;
                answer++;
            }
            
            // 위치 이동
            cx = nx;
            cy = ny;
        }
        return answer;
    }
    
    static boolean isEdge(int x,int y){
        return x<0 || y<0 || x>10 || y>10;
    }
}
