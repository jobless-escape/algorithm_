import java.util.*;

class Solution {
    public int solution(String[] storage, String[] requests) {
        int rows = storage.length;
        int cols = storage[0].length();

        char[][] grid = new char[rows][cols];
        for(int i = 0; i < rows; i++) {
            grid[i] = storage[i].toCharArray();
        }

        for(String request : requests) {
            if(request.length() == 1) {
                removeForkLift(grid, request.charAt(0));
            } else if(request.length() == 2) {
                removeCrane(grid, request.charAt(0), request.charAt(1));
            }
        }

        return countRemaining(grid);
    }

    private void removeForkLift(char[][] grid, char target) {
        int rows = grid.length;
        int cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<int[]> queue = new LinkedList<>();

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(i == 0 || i == rows-1 || j == 0 || j == cols-1) {
                    if(!visited[i][j]) {
                        visited[i][j] = true;

                        if(grid[i][j] == target) {
                            grid[i][j] = '.';
                        } else if(grid[i][j] == '.') {
                            queue.offer(new int[]{i, j});
                        }
                    }
                }
            }
        }

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0];
            int y = cur[1];

            for(int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if(nx >= 0 && nx < rows && ny >= 0 && ny < cols && !visited[nx][ny]) {
                    visited[nx][ny] = true;

                    if(grid[nx][ny] == '.') {
                        queue.offer(new int[]{nx, ny});
                    }
                    else if(grid[nx][ny] == target) {
                        grid[nx][ny] = '.';
                    }
                }
            }
        }
    }

    private void removeCrane(char[][] grid, char target1, char target2) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == target1 || grid[i][j] == target2) {
                    grid[i][j] = '.';
                }
            }
        }
    }

    private int countRemaining(char[][] grid) {
        int count = 0;
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] != '.') {
                    count++;
                }
            }
        }
        return count;
    }
}