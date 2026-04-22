import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int N = board.length;

        Queue<int[]> queue = new LinkedList<>();
        boolean[][][][] visited = new boolean[N][N][N][N];

        queue.offer(new int[]{0, 0, 0, 1, 0});
        visited[0][0][0][1] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r1 = cur[0], c1 = cur[1], r2 = cur[2], c2 = cur[3], cost = cur[4];

            if ((r1 == N-1 && c1 == N-1) || (r2 == N-1 && c2 == N-1)) {
                return cost;
            }

            List<int[]> nexts = new ArrayList<>();

            boolean isHorizontal = (r1 == r2);

            int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
            for (int[] d : dirs) {
                int nr1 = r1 + d[0], nc1 = c1 + d[1];
                int nr2 = r2 + d[0], nc2 = c2 + d[1];
                if (inRange(nr1, nc1, N) && inRange(nr2, nc2, N)
                        && board[nr1][nc1] == 0 && board[nr2][nc2] == 0) {
                    nexts.add(new int[]{nr1, nc1, nr2, nc2});
                }
            }

            if (isHorizontal) {
                if (r1 > 0 && board[r1-1][c1] == 0 && board[r2-1][c2] == 0) {
                    nexts.add(new int[]{r1-1, c1, r1, c1});
                    nexts.add(new int[]{r2-1, c2, r2, c2});
                }
                if (r1 < N-1 && board[r1+1][c1] == 0 && board[r2+1][c2] == 0) {
                    nexts.add(new int[]{r1, c1, r1+1, c1});
                    nexts.add(new int[]{r2, c2, r2+1, c2});
                }
            } else {
                if (c1 > 0 && board[r1][c1-1] == 0 && board[r2][c2-1] == 0) {
                    nexts.add(new int[]{r1, c1-1, r1, c1});
                    nexts.add(new int[]{r2, c2-1, r2, c2});
                }
                if (c1 < N-1 && board[r1][c1+1] == 0 && board[r2][c2+1] == 0) {
                    nexts.add(new int[]{r1, c1, r1, c1+1});
                    nexts.add(new int[]{r2, c2, r2, c2+1});
                }
            }

            for (int[] next : nexts) {
                int nr1 = next[0], nc1 = next[1], nr2 = next[2], nc2 = next[3];
                if (!visited[nr1][nc1][nr2][nc2]) {
                    visited[nr1][nc1][nr2][nc2] = true;
                    queue.offer(new int[]{nr1, nc1, nr2, nc2, cost + 1});
                }
            }
        }

        return -1;
    }

    boolean inRange(int r, int c, int N) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}