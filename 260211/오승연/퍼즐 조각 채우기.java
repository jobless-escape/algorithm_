import java.util.*;

class Solution {
    int n;
    boolean[][] visited;
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    public int solution(int[][] game_board, int[][] table) {
        n = game_board.length;

        // 1. 빈칸 블록 추출
        List<List<int[]>> blanks = extractBlocks(game_board, 0);

        // 2. 퍼즐 조각 추출
        List<List<int[]>> puzzles = extractBlocks(table, 1);

        boolean[] used = new boolean[puzzles.size()];
        int answer = 0;

        // 3. 빈칸과 퍼즐 조각 매칭
        for (List<int[]> blank : blanks) {
            normalize(blank);

            for (int i = 0; i < puzzles.size(); i++) {
                if (used[i]) continue;

                List<int[]> puzzle = puzzles.get(i);

                if (isMatch(blank, puzzle)) {
                    used[i] = true;
                    answer += blank.size();
                    break;
                }
            }
        }

        return answer;
    }

    // 블록 추출
    List<List<int[]>> extractBlocks(int[][] board, int target) {
        visited = new boolean[n][n];
        List<List<int[]>> blocks = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (!visited[i][j] && board[i][j] == target) {
                    blocks.add(bfs(board, i, j, target));
                }
            }
        }
        return blocks;
    }

    // 블록 추출할때 bfs
    List<int[]> bfs(int[][] board, int x, int y, int target) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> block = new ArrayList<>();

        q.offer(new int[]{x, y});
        visited[x][y] = true;
        block.add(new int[]{x, y});

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            for (int d = 0; d < 4; d++) {
                int nx = cur[0] + dx[d];
                int ny = cur[1] + dy[d];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    if (!visited[nx][ny] && board[nx][ny] == target) {
                        visited[nx][ny] = true;
                        q.offer(new int[]{nx, ny});
                        block.add(new int[]{nx, ny});
                    }
                }
            }
        }
        return block;
    }

    // 좌표 정규화
    void normalize(List<int[]> block) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        for (int[] p : block) {
            minX = Math.min(minX, p[0]);
            minY = Math.min(minY, p[1]);
        }

        for (int[] p : block) {
            p[0] -= minX;
            p[1] -= minY;
        }

        block.sort((a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return a[0] - b[0];
        });
    }

    // 블록 매칭 비교
    boolean isMatch(List<int[]> blank, List<int[]> puzzle) {
        if (blank.size() != puzzle.size()) return false;

        List<int[]> temp = copyBlock(puzzle);

        for (int r = 0; r < 4; r++) {
            normalize(temp);

            if (equalsBlock(blank, temp)) return true;

            temp = rotate(temp);
        }
        return false;
    }

    // 90도 회전
    List<int[]> rotate(List<int[]> block) {
        List<int[]> rotated = new ArrayList<>();
        for (int[] p : block) {
            rotated.add(new int[]{p[1], -p[0]});
        }
        return rotated;
    }

    // 빈 부분과 블록이 딱 들어맞나?
    boolean equalsBlock(List<int[]> a, List<int[]> b) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i)[0] != b.get(i)[0] || a.get(i)[1] != b.get(i)[1]) {
                return false;
            }
        }
        return true;
    }

    // 매칭비교할때 블록 복사
    List<int[]> copyBlock(List<int[]> block) {
        List<int[]> copy = new ArrayList<>();
        for (int[] p : block) {
            copy.add(new int[]{p[0], p[1]});
        }
        return copy;
    }
}
