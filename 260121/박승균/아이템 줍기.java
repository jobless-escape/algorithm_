import java.util.*;

class Solution {

    static int[][] map = new int[51][51];
    static boolean[][] visited = new boolean[51][51];

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {

        // 1. 테두리만 표시
        for (int[] r : rectangle) {
            int x1 = r[0];
            int y1 = r[1];
            int x2 = r[2];
            int y2 = r[3];

            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {

                    // 테두리 조건
                    if (x == x1 || x == x2 || y == y1 || y == y2) {
                        map[x][y] = 1;
                    }
                }
            }
        }

        // 2. 내부 제거 (다른 사각형 내부면 0)
        for (int[] r : rectangle) {
            int x1 = r[0];
            int y1 = r[1];
            int x2 = r[2];
            int y2 = r[3];

            for (int x = x1 + 1; x < x2; x++) {
                for (int y = y1 + 1; y < y2; y++) {
                    map[x][y] = 0;
                }
            }
        }

        // 3. BFS
        return bfs(characterX, characterY, itemX, itemY);
    }

    static int bfs(int sx, int sy, int ex, int ey) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{sx, sy, 0});
        visited[sx][sy] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0];
            int y = cur[1];
            int dist = cur[2];

            if (x == ex && y == ey) return dist;

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx < 1 || ny < 1 || nx > 50 || ny > 50) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] != 1) continue;

                visited[nx][ny] = true;
                q.offer(new int[]{nx, ny, dist + 1});
            }
        }
        return 0;
    }
}
