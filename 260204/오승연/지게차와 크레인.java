import java.io.*;
import java.util.*;

class Solution {
    static String[][] map;
    static int col,row,answer;
    static int[] xx={0,0,1,-1};
    static int[] yy={1,-1,0,0};
    static boolean[][] visited;
    static boolean[][] outside;
    
    public int solution(String[] storage, String[] requests) {
        row=storage.length;
        col=storage[0].length();
        answer=row*col;
        map=new String[row][col];
        for(int i=0;i<row;i++){
            String[] temp=storage[i].split("");
            for(int j=0;j<col;j++){
                map[i][j]=temp[j];
            }
        }
        for(int i=0;i<requests.length;i++){
            String[] temp=requests[i].split("");
            String st=temp[0];
            if(requests[i].length()>1){ //크레인
                for(int x=0;x<row;x++){
                    for(int y=0;y<col;y++){
                        if(map[x][y].equals(st)){
                            map[x][y]="0";
                            answer--;
                            System.out.println(x + ", " + y);
                        }
                    }
                }
            }else{ //외부와 닿아있는 것만
                // visited=new boolean[row][col];
                // for(int x=0;x<row;x++){
                //     for(int y=0;y<col;y++){
                //         if(map[x][y].equals(st)){
                //             bfs(x,y);
                //         }
                //     }
                // }
                bfsOutside();

                visited = new boolean[row][col];
                for (int x = 0; x < row; x++) {
                    for (int y = 0; y < col; y++) {
                        if (!map[x][y].equals(st)) continue;

                        for (int d = 0; d < 4; d++) {
                            int nx = x + xx[d];
                            int ny = y + yy[d];

                            // 격자 밖 = 외부
                            if (nx < 0 || ny < 0 || nx >= row || ny >= col) {
                                visited[x][y] = true;
                                break;
                            }
                            // 외부 공기와 접촉
                            if (outside[nx][ny]) {
                                visited[x][y] = true;
                                break;
                            }
                        }
                    }
                }

                
                for(int x=0;x<row;x++){
                    for(int y=0;y<col;y++){
                        if(visited[x][y]){
                            map[x][y]="0";
                            answer--;
                            System.out.println(x + ", " + y);
                        }
                    }
                }
            }
        }
        return answer;
    }
//     static void bfs(int x, int y){
//         for(int i=0;i<4;i++){
//             int nx=x+xx[i];
//             int ny=y+yy[i];
            
//             if(isEdge(nx,ny)||map[nx][ny].equals("0")){
//                 visited[x][y]=true;
//                 break;
//             }
//         }
//     }
    

    static void bfsOutside() {
        outside = new boolean[row][col];
        Queue<int[]> q = new LinkedList<>();

        // 테두리에서 시작
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (i != 0 && j != 0 && i != row - 1 && j != col - 1) continue;
                if (!map[i][j].equals("0")) continue;

                outside[i][j] = true;
                q.add(new int[]{i, j});
            }
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + xx[d];
                int ny = cur[1] + yy[d];

                if (nx < 0 || ny < 0 || nx >= row || ny >= col) continue;
                if (outside[nx][ny]) continue;
                if (!map[nx][ny].equals("0")) continue;

                outside[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }
    }



    static boolean isEdge(int x, int y){
        return x<0||y<0||x>=row||y>=col;
    }
}
