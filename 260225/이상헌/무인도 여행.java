import java.util.*;

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int[] solution(String[] maps) {
        int n = maps.length;
        int m = maps[0].length();
        boolean[][] visited = new boolean[n][m];
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && maps[i].charAt(j) != 'X') {
                    result.add(bfs(maps, visited, i, j, n, m));
                }
            }
        }

        if (result.isEmpty()) return new int[]{-1};

        Collections.sort(result);
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    static int bfs(String[] maps, boolean[][] visited, int startX, int startY, int n, int m) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY});
        visited[startX][startY] = true;
        int total = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            total += maps[cur[0]].charAt(cur[1]) - '0';

            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (nx < 0 || ny < 0 || nx >= n || ny >= m) continue;
                if (visited[nx][ny] || maps[nx].charAt(ny) == 'X') continue;

                visited[nx][ny] = true;
                queue.add(new int[]{nx, ny});
            }
        }

        return total;
    }
}