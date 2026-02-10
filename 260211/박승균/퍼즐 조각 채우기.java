import java.util.*;

class Diagram {
    List<List<int[]>> points;
    
    Diagram(){
        points = new ArrayList<>();
    }
}

class Solution {
    static int dxdy[][] = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    static boolean visited[][];
    static int n, m;
    static int[][] game_board;
    static int[][] table;
    static List<Diagram> diagrams = new ArrayList<>();
    
    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        n = game_board.length;
        m = game_board[0].length;
        this.game_board = game_board;
        this.table = table;
        visited = new boolean[n][m];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(!visited[i][j] && table[i][j] == 1){
                    // 도형 찾기
                    findDiagram(i, j);
                }
            }
        }
        
        boolean[] used = new boolean[diagrams.size()];
        // 도형 넣기
        visited = new boolean[n][m];

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(!visited[i][j] && game_board[i][j] == 0){
                    List<int[]> emptyShape = findEmpty(i,j);
                    answer += match(emptyShape, used);
                }
            }
        }
        
        
        return answer;
    }
    
    static void findDiagram(int x, int y){
        List<int[]> list = new ArrayList<>();
        List<int[]> nlist = new ArrayList<>();
        
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;
        int minX = x; int minY = y;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int curX = cur[0]; int curY = cur[1];
            minX = Math.min(minX, curX);
            minY = Math.min(minY, curY);
            list.add(new int[]{curX, curY});
            for(int[] d: dxdy){
                int nx = curX + d[0]; int ny = curY + d[1];
                if(!isEdge(nx, ny) && !visited[nx][ny] && table[nx][ny] == 1){
                    visited[nx][ny] = true;
                        q.add(new int[]{nx, ny});
                }
            }            
        }
        
        //list 정규화
        for(int[] point: list){
            nlist.add(new int[]{point[0] - minX, point[1] - minY});
            // System.out.println((point[0] - minX) + " " + (point[1] - minY));
        }
        
        // 정규화 한거 다이어그램에 4방향 추가
        List<int[]> rotated = new ArrayList<>(nlist);

        Diagram diagram = new Diagram();

        for (int r = 0; r < 4; r++) {

            List<int[]> shape = new ArrayList<>();
            int minRX = Integer.MAX_VALUE;
            int minRY = Integer.MAX_VALUE;

            for (int[] p : rotated) {
                int rx = p[1];
                int ry = -p[0];
                shape.add(new int[]{rx, ry});
                minRX = Math.min(minRX, rx);
                minRY = Math.min(minRY, ry);
            }

            for (int[] p : shape) {
                p[0] -= minRX;
                p[1] -= minRY;
            }

            // 정렬
            shape.sort((a, b) -> {
                if (a[0] == b[0]) return a[1] - b[1];
                return a[0] - b[0];
            });

            diagram.points.add(shape);

            // 갱신
            rotated = shape;
        }

        diagrams.add(diagram);
    }
    
    static List<int[]> findEmpty(int x, int y){
        List<int[]> list = new ArrayList<>();
        List<int[]> nlist = new ArrayList<>();

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        visited[x][y] = true;

        int minX = x, minY = y;

        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cx = cur[0];
            int cy = cur[1];

            list.add(new int[]{cx, cy});
            minX = Math.min(minX, cx);
            minY = Math.min(minY, cy);

            for(int[] d : dxdy){
                int nx = cx + d[0];
                int ny = cy + d[1];

                if(!isEdge(nx, ny) && !visited[nx][ny] && game_board[nx][ny] == 0){
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }

        // 정규화
        for(int[] p : list){
            nlist.add(new int[]{p[0]-minX, p[1]-minY});
        }

        // 정렬
        nlist.sort((a,b)->{
            if(a[0]==b[0]) return a[1]-b[1];
            return a[0]-b[0];
        });

        return nlist;
    }
    
    static boolean isSame(List<int[]> a, List<int[]> b){
        if(a.size() != b.size()) return false;

        for(int i=0;i<a.size();i++){
            if(a.get(i)[0] != b.get(i)[0]) return false;
            if(a.get(i)[1] != b.get(i)[1]) return false;
        }
        return true;
    }
    
    static int match(List<int[]> emptyShape, boolean[] used) {
        for(int k = 0; k < diagrams.size(); k++){
            if(used[k]) continue;

            Diagram d = diagrams.get(k);

            for(List<int[]> shape : d.points){
                if(isSame(emptyShape, shape)){
                    used[k] = true;
                    return shape.size();
                }
            }
        }

        return 0;
    }
    
    static boolean isEdge(int x, int y){
        return x < 0 || x >= n || y < 0 || y >= m;
    }
}