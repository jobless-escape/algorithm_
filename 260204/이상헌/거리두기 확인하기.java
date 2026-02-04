import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int[] solution(String[][] places) {
        int[] answer = new int[5];

        for (int i = 0; i < 5; i++) {
            if (check(places[i])) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }

        return answer;
    }

    boolean check(String[] place) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (place[i].charAt(j) == 'P') {
                    if (!bfs(i, j, place)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    boolean bfs(int x, int y, String[] place) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[5][5];

        queue.offer(new int[]{x, y, 0});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curX = cur[0];
            int curY = cur[1];
            int dist = cur[2];

            if (dist > 2) continue;

            if (dist > 0 && place[curX].charAt(curY) == 'P') {
                return false;
            }

            if (dist < 2) {
                for (int i = 0; i < 4; i++) {
                    int nx = curX + dx[i];
                    int ny = curY + dy[i];

                    if (nx < 0 || nx >= 5 || ny < 0 || ny >= 5) continue;

                    if (visited[nx][ny]) continue;

                    if (place[nx].charAt(ny) == 'X') continue;

                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, dist + 1});
                }
            }
        }

        return true;
    }
}