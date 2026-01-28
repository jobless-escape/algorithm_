import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = n;

        for (int i = 0; i < wires.length; i++) {
            List<Integer>[] graph = new ArrayList[n + 1];
            for (int j = 1; j <= n; j++) {
                graph[j] = new ArrayList<>();
            }

            for (int j = 0; j < wires.length; j++) {
                if (i == j) continue;
                int v1 = wires[j][0];
                int v2 = wires[j][1];
                graph[v1].add(v2);
                graph[v2].add(v1);
            }

            int count = bfs(1, graph, n);

            int diff = Math.abs(count - (n - count));
            answer = Math.min(answer, diff);
        }

        return answer;
    }

    private int bfs(int start, List<Integer>[] graph, int n) {
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;
        int count = 1;

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph[cur]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                    count++;
                }
            }
        }

        return count;
    }
}