import java.util.*;

class Solution {
    static class Robot {
        int r1, c1, r2, c2, cnt;
        
        Robot(int r1, int c1, int r2, int c2, int cnt) {
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
            this.cnt = cnt;
        }
    }
    
    public int solution(int[][] board) {
        int n = board.length;
        Queue<Robot> q = new LinkedList<>();
        boolean[][][][] visited = new boolean[n][n][n][n];
        
        q.add(new Robot(0, 0, 0, 1, 0));
        visited[0][0][0][1] = true;
        
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        while (!q.isEmpty()) {
            Robot cur = q.poll();
            
            // 목표 도달
            if ((cur.r1 == n-1 && cur.c1 == n-1) || 
                (cur.r2 == n-1 && cur.c2 == n-1)) {
                return cur.cnt;
            }
            
            // 1. 상하좌우 이동
            for (int d = 0; d < 4; d++) {
                int nr1 = cur.r1 + dr[d];
                int nc1 = cur.c1 + dc[d];
                int nr2 = cur.r2 + dr[d];
                int nc2 = cur.c2 + dc[d];
                
                if (isValid(nr1, nc1, n) && isValid(nr2, nc2, n) &&
                    board[nr1][nc1] == 0 && board[nr2][nc2] == 0) {
                    
                    if (!visited[nr1][nc1][nr2][nc2]) {
                        visited[nr1][nc1][nr2][nc2] = true;
                        q.add(new Robot(nr1, nc1, nr2, nc2, cur.cnt + 1));
                    }
                }
            }
            
            // 2. 회전
            // 가로 상태
            if (cur.r1 == cur.r2) {
                for (int d = -1; d <= 1; d += 2) {
                    int nr = cur.r1 + d;
                    
                    if (isValid(nr, cur.c1, n) && isValid(nr, cur.c2, n) &&
                        board[nr][cur.c1] == 0 && board[nr][cur.c2] == 0) {
                        
                        // 왼쪽 기준 회전
                        if (!visited[cur.r1][cur.c1][nr][cur.c1]) {
                            visited[cur.r1][cur.c1][nr][cur.c1] = true;
                            q.add(new Robot(cur.r1, cur.c1, nr, cur.c1, cur.cnt + 1));
                        }
                        
                        // 오른쪽 기준 회전
                        if (!visited[cur.r2][cur.c2][nr][cur.c2]) {
                            visited[cur.r2][cur.c2][nr][cur.c2] = true;
                            q.add(new Robot(cur.r2, cur.c2, nr, cur.c2, cur.cnt + 1));
                        }
                    }
                }
            }
            
            // 세로 상태
            else {
                for (int d = -1; d <= 1; d += 2) {
                    int nc = cur.c1 + d;
                    
                    if (isValid(cur.r1, nc, n) && isValid(cur.r2, nc, n) &&
                        board[cur.r1][nc] == 0 && board[cur.r2][nc] == 0) {
                        
                        // 위 기준 회전
                        if (!visited[cur.r1][cur.c1][cur.r1][nc]) {
                            visited[cur.r1][cur.c1][cur.r1][nc] = true;
                            q.add(new Robot(cur.r1, cur.c1, cur.r1, nc, cur.cnt + 1));
                        }
                        
                        // 아래 기준 회전
                        if (!visited[cur.r2][cur.c2][cur.r2][nc]) {
                            visited[cur.r2][cur.c2][cur.r2][nc] = true;
                            q.add(new Robot(cur.r2, cur.c2, cur.r2, nc, cur.cnt + 1));
                        }
                    }
                }
            }
        }
        
        return 0;
    }
    
    private boolean isValid(int r, int c, int n) {
        return r >= 0 && c >= 0 && r < n && c < n;
    }
}
