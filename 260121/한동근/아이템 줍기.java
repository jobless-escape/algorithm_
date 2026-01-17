import java.util.*;

class Solution {
    static boolean[][] visited;
    static int[][] map;
    static int mapSize = 101;
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

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;

        map = new int[mapSize][mapSize];
        visited = new boolean[mapSize][mapSize];

        for(int i = 0 ; i < mapSize; i++) Arrays.fill(map[i], 0);

        for(int i = 0 ; i < rectangle.length; i++){
            int lx = rectangle[i][0] * 2;
            int ly = rectangle[i][1] * 2;
            int rx = rectangle[i][2] * 2;
            int ry = rectangle[i][3] * 2;

            for(int j = lx ; j <= rx ; j++){
                for(int k = ly ; k <= ry ; k++){
                    map[j][k] = 1;
                }
            }
        }

        for(int i = 0 ; i < rectangle.length ; i++){
            int lx = rectangle[i][0] * 2;
            int ly = rectangle[i][1] * 2;
            int rx = rectangle[i][2] * 2;
            int ry = rectangle[i][3] * 2;

            for(int j = lx + 1 ; j < rx ; j++){
                for(int k = ly + 1 ; k < ry ; k++){
                    map[j][k] = 0;
                }
            }
        }

        answer = bfs(characterX * 2, characterY * 2, itemX * 2, itemY * 2);

        if(answer % 2 == 1) answer = (answer / 2) + 1;
        else answer /= 2;

        return answer;
    }

    static int bfs(int characterX, int characterY, int itemX, int itemY){
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(characterX, characterY, 0));
        visited[characterX][characterY] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            if(now.x == itemX && now.y == itemY) return now.dist;

            for(int i = 0 ; i < 4 ; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(!isValid(nx, ny)) continue;

                if(!visited[nx][ny] && map[nx][ny] == 1){
                    visited[nx][ny] = true;
                    q.offer(new Node(nx, ny, now.dist + 1));
                }
            }
        }
        return 0;
    }

    static boolean isValid(int x, int y){
        return x >= 1 && x <= 100 && y >= 1 && y <= 100;
    }
}