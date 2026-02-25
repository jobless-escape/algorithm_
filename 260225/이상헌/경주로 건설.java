import java.util.*;

class Solution {
    // 상 하 좌 우
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public int solution(int[][] board) {
        int n = board.length;
        int[][][] dist = new int[n][n][4];
        for (int[][] d2 : dist)
            for (int[] d1 : d2)
                Arrays.fill(d1, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        dist[0][0][1] = 0;
        dist[0][0][3] = 0;
        pq.offer(new int[]{0, 0, 0, 1});
        pq.offer(new int[]{0, 0, 0, 3});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int cost = cur[0], y = cur[1], x = cur[2], dir = cur[3];

            if (dist[y][x][dir] < cost) continue;

            for (int nd = 0; nd < 4; nd++) {
                int ny = y + dy[nd];
                int nx = x + dx[nd];
                if (ny < 0 || nx < 0 || ny >= n || nx >= n) continue;
                if (board[ny][nx] == 1) continue; // 벽

                int addCost = (dir == nd) ? 100 : 600;
                int newCost = cost + addCost;

                if (newCost < dist[ny][nx][nd]) {
                    dist[ny][nx][nd] = newCost;
                    pq.offer(new int[]{newCost, ny, nx, nd});
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int d = 0; d < 4; d++) {
            answer = Math.min(answer, dist[n-1][n-1][d]);
        }
        return answer;
    }
}