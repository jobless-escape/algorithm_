import java.util.*;

class Solution {
    static int[][] land;
    static int[][] dxdy = {{-1,0},{1,0},{0,-1},{0,1}};
    static int n, m;

    public int solution(int[][] land) {
        this.land = land;
        n = land.length;
        m = land[0].length;

        // 1. 덩어리 계산
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(land[i][j] == 1){
                    calcLand(i, j);
                }
            }
        }

        // 2. 열마다 계산
        int max = 0;
        for(int col = 0; col < m; col++){
            max = Math.max(max, calcCol(col));
        }

        return max;
    }

    public void calcLand(int x, int y){

        Queue<int[]> q = new ArrayDeque<>();
        List<int[]> list = new ArrayList<>();

        q.add(new int[]{x,y});
        list.add(new int[]{x,y});
        land[x][y] = 0;

        int cnt = 1;

        // BFS
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0], cy = cur[1];

            for(int[] d : dxdy){
                int nx = cx + d[0];
                int ny = cy + d[1];

                if(nx>=0 && nx<n && ny>=0 && ny<m && land[nx][ny]==1){
                    land[nx][ny] = 0;
                    q.add(new int[]{nx,ny});
                    list.add(new int[]{nx,ny});
                    cnt++;
                }
            }
        }

        // HashMap 대신 배열 사용
        int[] top = new int[m];
        Arrays.fill(top, -1);

        for(int[] pos : list){
            int row = pos[0];
            int col = pos[1];

            if(top[col] == -1 || top[col] > row){
                top[col] = row;
            }
        }

        // 위쪽 한 칸에만 size 기록
        for(int col = 0; col < m; col++){
            if(top[col] != -1){
                land[top[col]][col] = cnt;
            }
        }
    }

    static int calcCol(int col){
        int sum = 0;
        for(int row = 0; row < n; row++){
            sum += land[row][col];
        }
        return sum;
    }
}
