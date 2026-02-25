import java.util.*;

class Solution {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int n, m;
    static int[][] group;
    static Map<Integer, Integer> groupSize;

    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        group = new int[n][m];
        groupSize = new HashMap<>();

        int groupId = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && group[i][j] == 0) {
                    int size = bfs(land, i, j, groupId);
                    groupSize.put(groupId, size);
                    groupId++;
                }
            }
        }

        int maxOil = 0;

        for (int col = 0; col < m; col++) {
            Set<Integer> visitedGroups = new HashSet<>();
            int oil = 0;

            for (int row = 0; row < n; row++) {
                if (group[row][col] > 0) {
                    int gId = group[row][col];
                    if (!visitedGroups.contains(gId)) {
                        visitedGroups.add(gId);
                        oil += groupSize.get(gId);
                    }
                }
            }

            maxOil = Math.max(maxOil, oil);
        }

        return maxOil;
    }

    private int bfs(int[][] land, int startX, int startY, int groupId) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});
        group[startX][startY] = groupId;
        int size = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m
                        && land[nx][ny] == 1 && group[nx][ny] == 0) {
                    group[nx][ny] = groupId;
                    queue.offer(new int[]{nx, ny});
                    size++;
                }
            }
        }

        return size;
    }
}