import java.util.*;

class Solution {
    static Queue<int[]>[] q;
    static int answer = 0;
    static int size;

    public int solution(int[][] points, int[][] routes) {
        size = routes.length;
        q = new LinkedList[size];

        for(int i = 0 ; i < size ; i++){
            q[i] = new LinkedList<>();
        }

        recordMove(points, routes);
        findCollision();

        return answer;
    }

    static void recordMove(int[][] points, int[][] routes){
        for(int i = 0 ; i < size ; i++){
            int from = routes[i][0] - 1;
            int fromR = points[from][0] - 1;
            int fromC = points[from][1] - 1;

            q[i].add(new int[]{fromR, fromC});

            for(int j = 1 ; j < routes[i].length ; j++){
                int to = routes[i][j] - 1;
                int toR = points[to][0] - 1;
                int toC = points[to][1] - 1;

                while(fromR != toR){
                    if(fromR < toR) fromR++;
                    else fromR--;
                    q[i].add(new int[]{fromR, fromC});
                }

                while(fromC != toC){
                    if(fromC < toC) fromC++;
                    else fromC--;
                    q[i].add(new int[]{fromR, fromC});
                }
            }
        }
    }

    static void findCollision(){
        int escapeRobot = 0;
        while(escapeRobot != size){
            int[][] map = new int[101][101];
            escapeRobot = 0;

            for(int i = 0 ; i < size ; i++){
                if(q[i].isEmpty()){
                    escapeRobot++;
                    continue;
                }

                int[] temp = q[i].poll();
                map[temp[0]][temp[1]]++;
            }

            for(int i = 0 ; i < 101 ; i++){
                for(int j = 0 ; j < 101 ; j++){
                    if(map[i][j] >= 2) answer++;
                }
            }
        }
    }
}