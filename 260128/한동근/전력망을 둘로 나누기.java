import java.util.*;

class Solution {
    static int answer = Integer.MAX_VALUE;
    static ArrayList<ArrayList<Integer>> list;

    public int solution(int n, int[][] wires) {

        list = new ArrayList<>();

        for(int i = 0 ; i <= n ; i++){
            list.add(new ArrayList<>());
        }

        for(int i = 0 ; i < wires.length ; i++){
            int a = wires[i][0];
            int b = wires[i][1];
            list.get(a).add(b);
            list.get(b).add(a);
        }

        for(int i = 0 ; i < wires.length ; i++){
            int a = wires[i][0];
            int b = wires[i][1];
            bfs(a, b, n);
        }

        return answer;
    }

    static void bfs(int a, int b, int n){
        Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];
        q.add(a);
        visited[a] = true;
        int cnt = 0;

        while(!q.isEmpty()){
            int now = q.poll();
            cnt++;
            for(int num : list.get(now)){
                if(num == b || visited[num]) continue;
                visited[num] = true;
                q.add(num);
            }
        }

        answer = Math.min(answer, Math.abs(n - 2 * cnt));
    }
}