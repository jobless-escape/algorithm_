import java.util.*;

class Solution {
    public int solution(int m, int n, String[] board) {
        char[][] grid = new char[m][n];
        for (int i = 0; i < m; i++) {
            grid[i] = board[i].toCharArray();
        }

        int answer = 0;

        while (true) {
            boolean[][] toRemove = new boolean[m][n];
            boolean found = false;

            for (int r = 0; r < m - 1; r++) {
                for (int c = 0; c < n - 1; c++) {
                    char ch = grid[r][c];
                    if (ch == ' ') continue;

                    if (ch == grid[r][c+1] &&
                            ch == grid[r+1][c] &&
                            ch == grid[r+1][c+1]) {
                        toRemove[r][c]     = true;
                        toRemove[r][c+1]   = true;
                        toRemove[r+1][c]   = true;
                        toRemove[r+1][c+1] = true;
                        found = true;
                    }
                }
            }

            if (!found) break;

            for (int r = 0; r < m; r++) {
                for (int c = 0; c < n; c++) {
                    if (toRemove[r][c]) {
                        grid[r][c] = ' ';
                        answer++;
                    }
                }
            }

            for (int c = 0; c < n; c++) {
                List<Character> blocks = new ArrayList<>();
                for (int r = m - 1; r >= 0; r--) {
                    if (grid[r][c] != ' ') {
                        blocks.add(grid[r][c]);
                    }
                }
                for (int r = m - 1; r >= 0; r--) {
                    int idx = m - 1 - r;
                    if (idx < blocks.size()) {
                        grid[r][c] = blocks.get(idx);
                    } else {
                        grid[r][c] = ' ';
                    }
                }
            }
        }

        return answer;
    }
}