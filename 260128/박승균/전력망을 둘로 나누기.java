import java.util.*;

class Solution {
    List<Integer>[] graph;
    boolean[] visited;
    int count;

    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];

            visited = new boolean[n + 1];
            count = 0;

            dfs(a, a, b);

            int diff = Math.abs(count - (n - count));
            answer = Math.min(answer, diff);
        }

        return answer;
    }

    void dfs(int node, int cutA, int cutB) {
        visited[node] = true;
        count++;

        for (int next : graph[node]) {
            if ((node == cutA && next == cutB) || 
                (node == cutB && next == cutA)) continue;

            if (!visited[next]) {
                dfs(next, cutA, cutB);
            }
        }
    }
}
