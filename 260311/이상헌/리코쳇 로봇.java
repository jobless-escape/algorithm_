import java.util.*;

class Solution {
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public int solution(String[] board) {
        int rows = board.length;
        int cols = board[0].length();

        int startY = 0, startX = 0, endY = 0, endX = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char c = board[i].charAt(j);
                if (c == 'R') { startY = i; startX = j; }
                if (c == 'G') { endY = i; endX = j; }
            }
        }

        int[][] visited = new int[rows][cols];
        for (int[] row : visited) Arrays.fill(row, -1);

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startY, startX});
        visited[startY][startX] = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cy = cur[0], cx = cur[1];

            if (cy == endY && cx == endX) return visited[cy][cx];

            for (int d = 0; d < 4; d++) {
                int ny = cy, nx = cx;

                while (true) {
                    int nextY = ny + dy[d];
                    int nextX = nx + dx[d];

                    if (nextY < 0 || nextY >= rows || nextX < 0 || nextX >= cols) break;
                    if (board[nextY].charAt(nextX) == 'D') break;

                    ny = nextY;
                    nx = nextX;
                }

                if (ny == cy && nx == cx) continue;

                if (visited[ny][nx] == -1) {
                    visited[ny][nx] = visited[cy][cx] + 1;
                    queue.offer(new int[]{ny, nx});
                }
            }
        }

        return -1;
    }
}