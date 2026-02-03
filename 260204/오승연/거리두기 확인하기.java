import java.io.*;
import java.util.*;

class Solution {
    static int len;
    static String[][] map;
    static int[] xx={0,0,1,-1,1,-1,1,-1};
    static int[] yy={1,-1,0,0,1,-1,-1,1};
    
    public int[] solution(String[][] places) {
        len=places.length;
        int[] answer = {1,1,1,1,1};
        //만약 (맵을초과하면) continue,
        //만약 대각선에 P 있으면, 만약 P들 사이가 하나라도 O이라면, 멈추고 0출력
        //만약 P의 좌우위아래에 P 있으면, 멈추고 0출력
        //만약 P의 좌우위아래에 O 있으면, 만약 O에서 한칸 갔을때 P라면, 멈추고 0출력
        for(int i=0;i<len;i++){
            map=new String[len][len];
            for(int j=0;j<len;j++){
                map[j]=places[i][j].split("");
            }
            out:
            for(int x=0;x<len;x++){
                for(int y=0;y<len;y++){
                    if(map[x][y].equals("P")){
                        boolean flag=bfs(x,y);
                        if(flag){
                            answer[i]=0;
                            break out;
                        }
                    }
                }
            }
        }
        
        return answer;
    }
    static boolean bfs(int x, int y){
        Queue<int[]> q=new LinkedList<>();
        q.offer(new int[]{x,y});
        
        while(!q.isEmpty()){
            int[] xy=q.poll();
            int cx=xy[0];
            int cy=xy[1];
            
            for(int i=0;i<8;i++){
                int nx=cx+xx[i];
                int ny=cy+yy[i];
                if(isEdge(nx,ny)) continue;
                if(i<4){//좌우위아래
                    if(map[nx][ny].equals("P")){
                        return true; // 거리 1 위반
                    }
                    if(map[nx][ny].equals("O")){
                        int nnx = nx + xx[i];
                        int nny = ny + yy[i];
                        if(isEdge(nnx,nny)) continue;
                        if(map[nnx][nny].equals("P")){
                            return true; // 거리 2 위반
                        }
                    }
                }else{//대각선
                    if(map[nx][ny].equals("P")){
                        // 사이에 있는 두 칸
                        if(map[cx][ny].equals("O") || map[nx][cy].equals("O")){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    static boolean isEdge(int x, int y){
        return x<0||y<0||x>=len||y>=len;
    }
}
