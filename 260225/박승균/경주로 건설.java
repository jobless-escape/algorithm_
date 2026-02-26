import java.util.*;

class Solution {
    static int n;
    static int dxdy[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        
    public int solution(int[][] board) {
        int answer = 0;
        n = board.length;
        int[][][] cost = new int[n][n][4];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }
        cost[0][0][0] = 0; cost[0][0][2] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        pq.add(new int[]{0, 0, 0, 0}); // x, y, cost, dir
        pq.add(new int[]{0, 0, 0, 2}); 
        // dir은 0 동, 1 서, 2 남, 3 북
        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            int curX = cur[0]; int curY = cur[1];
            int curCost = cur[2]; int curDir = cur[3];
            if(curX == n-1 && curY == n-1) return curCost;
            
            for(int i = 0; i < 4; i++){
                int[] d = dxdy[i];
                int nDir = i; int nx = curX + d[0]; int ny = curY + d[1];
                if(isEdge(nx, ny) || board[nx][ny] == 1) continue; // 벽조건
                
                int nextCost;
                if(nDir == curDir) nextCost = curCost + 100;
                else nextCost = curCost + 600;
                
                if(cost[nx][ny][nDir] > nextCost) {
                    cost[nx][ny][nDir] = nextCost;
                    pq.add(new int[]{nx, ny, nextCost, nDir});
                }
            }
        }
        
        
        return answer;
    }
    
    static boolean isEdge(int x, int y){
        return x < 0 || x >= n || y < 0 || y >= n;
    }
}