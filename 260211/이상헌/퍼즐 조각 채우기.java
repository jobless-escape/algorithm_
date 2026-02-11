import java.util.*;

class Solution {
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public int solution(int[][] game_board, int[][] table) {
        int n = game_board.length;

        List<List<int[]>> emptySpaces = extractShapes(game_board, 0, n);

        List<List<int[]>> blocks = extractShapes(table, 1, n);

        List<List<int[]>> normalizedBlocks = new ArrayList<>();
        for (List<int[]> block : blocks) {
            normalizedBlocks.add(normalize(block));
        }

        boolean[] usedBlock = new boolean[normalizedBlocks.size()];
        int answer = 0;

        for (List<int[]> space : emptySpaces) {
            List<int[]> normalizedSpace = normalize(space);

            for (int i = 0; i < normalizedBlocks.size(); i++) {
                if (usedBlock[i]) continue;

                List<int[]> rotated = normalizedBlocks.get(i);
                boolean matched = false;

                for (int r = 0; r < 4; r++) {
                    if (isSameShape(normalizedSpace, rotated)) {
                        matched = true;
                        break;
                    }
                    rotated = normalize(rotate(rotated));
                }

                if (matched) {
                    usedBlock[i] = true;
                    answer += normalizedSpace.size();
                    break;
                }
            }
        }

        return answer;
    }

    private List<List<int[]>> extractShapes(int[][] board, int target, int n) {
        boolean[][] visited = new boolean[n][n];
        List<List<int[]>> shapes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == target && !visited[i][j]) {
                    shapes.add(bfs(board, visited, i, j, target, n));
                }
            }
        }
        return shapes;
    }

    private List<int[]> bfs(int[][] board, boolean[][] visited, int sr, int sc, int target, int n) {
        Queue<int[]> queue = new LinkedList<>();
        List<int[]> shape = new ArrayList<>();

        queue.offer(new int[]{sr, sc});
        visited[sr][sc] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            shape.add(cur);

            for (int d = 0; d < 4; d++) {
                int nr = cur[0] + dr[d];
                int nc = cur[1] + dc[d];

                if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
                if (visited[nr][nc] || board[nr][nc] != target) continue;

                visited[nr][nc] = true;
                queue.offer(new int[]{nr, nc});
            }
        }
        return shape;
    }

    private List<int[]> normalize(List<int[]> shape) {
        int minR = Integer.MAX_VALUE, minC = Integer.MAX_VALUE;

        for (int[] cell : shape) {
            minR = Math.min(minR, cell[0]);
            minC = Math.min(minC, cell[1]);
        }

        List<int[]> normalized = new ArrayList<>();
        for (int[] cell : shape) {
            normalized.add(new int[]{cell[0] - minR, cell[1] - minC});
        }

        normalized.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);
        return normalized;
    }

    private List<int[]> rotate(List<int[]> shape) {
        List<int[]> rotated = new ArrayList<>();
        for (int[] cell : shape) {
            rotated.add(new int[]{cell[1], -cell[0]});
        }
        return rotated;
    }

    private boolean isSameShape(List<int[]> a, List<int[]> b) {
        if (a.size() != b.size()) return false;

        for (int i = 0; i < a.size(); i++) {
            if (a.get(i)[0] != b.get(i)[0] || a.get(i)[1] != b.get(i)[1]) {
                return false;
            }
        }
        return true;
    }
}