import java.util.*;

class Solution {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static ArrayList<Integer> answer;

    public int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        map = new int[n][m];
        visited = new boolean[n][m];
        answer = new ArrayList<>();

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                char text = maps[i].charAt(j);
                if(text == 'X') map[i][j] = 0;
                else map[i][j] = text - '0';
            }
        }

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(map[i][j] > 0 && !visited[i][j]) bfs(i, j);
            }
        }

        if(answer.size() > 0){
            Collections.sort(answer);
            int[] answerArr = new int[answer.size()];

            for(int i = 0 ; i < answer.size() ; i++){
                answerArr[i] = answer.get(i);
            }

            return answerArr;
        }

        return new int[] {-1};
    }

    static void bfs(int i, int j){
        int days = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {i, j});
        visited[i][j] = true;

        while(!q.isEmpty()){
            int[] now = q.poll();
            days += map[now[0]][now[1]];

            for(int index = 0 ; index < 4 ; index++){
                int nx = now[0] + dx[index];
                int ny = now[1] + dy[index];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;

                if(map[nx][ny] > 0 && !visited[nx][ny]){
                    visited[nx][ny] = true;
                    q.offer(new int[] {nx, ny});
                }
            }
        }

        answer.add(days);
    }
}