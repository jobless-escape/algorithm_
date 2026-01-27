import java.util.*;

class Solution {
    static int answer = Integer.MIN_VALUE;
    static ArrayList<Integer>[] list;

    public int solution(int[] info, int[][] edges) {
        list = new ArrayList[info.length];

        for(int i = 0 ; i < info.length ; i++) list[i] = new ArrayList<>();

        for(int[] edge : edges) list[edge[0]].add(edge[1]);

        HashSet<Integer> hs = new HashSet<>();
        hs.add(0);

        dfs(0, 0, info, hs);
        return answer;
    }

    static void dfs(int sheep, int wolf, int[] info, HashSet<Integer> canGo){
        answer = Math.max(answer, sheep);

        for(int node : canGo){
            int sheepCount = sheep;
            int wolfCount = wolf;

            if(info[node] == 0) sheepCount++;
            else wolfCount++;

            if(wolfCount >= sheepCount) continue;

            HashSet<Integer> next = new HashSet<>(canGo);
            next.remove(node);
            for(int nextNum : list[node]) next.add(nextNum);
            dfs(sheepCount, wolfCount, info, next);
        }
    }
}