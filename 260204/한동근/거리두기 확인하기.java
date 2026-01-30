import java.util.*;

class Solution {
    static boolean[][] visited;
    static char[][] map;
    static int isManhattan;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    static class Node{
        int x, y, dist;
        
        public Node(int x, int y, int dist){
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for(int index = 0; index < places.length ; index++){
            map = new char[5][5];
            
            for(int i = 0 ; i < 5 ; i++){
                for(int j = 0 ; j < 5 ; j++){
                    map[i][j] = places[index][i].charAt(j);
                }
            }
            
            isManhattan = 1;
            for(int i = 0 ; i < 5 ; i++){
                for(int j = 0 ; j < 5 ; j++){
                    if(map[i][j] == 'P') bfs(i, j);
                }
            }
            answer[index] = isManhattan;
        }
        
        return answer;
    }
    
    static void bfs(int x, int y){
        Queue<Node> q = new ArrayDeque<>();
        visited = new boolean[5][5];
        visited[x][y] = true;
        q.offer(new Node(x, y, 0));
        
        while(!q.isEmpty()){
            Node now = q.poll();
            
            for(int i = 0 ; i < 4 ; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                
                if(nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;
                if(map[nx][ny] == 'O' && !visited[nx][ny] && now.dist < 2){
                    q.offer(new Node(nx, ny, now.dist + 1));
                    visited[nx][ny] = true;
                }
                
                if(map[nx][ny] == 'P' && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    if(Math.abs(nx - x) + Math.abs(ny - y) <= 2){
                        isManhattan = 0;
                        return;
                    }
                }
            }
        }
    }
}