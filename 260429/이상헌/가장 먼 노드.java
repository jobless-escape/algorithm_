import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {

        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) graph[i] = new ArrayList<>();

        for (int[] e : edge) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        dist[1] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int next : graph[cur]) {
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1;
                    queue.offer(next);
                }
            }
        }

        int maxDist = 0;
        for (int i = 2; i <= n; i++) {
            maxDist = Math.max(maxDist, dist[i]);
        }

        int answer = 0;
        for (int i = 2; i <= n; i++) {
            if (dist[i] == maxDist) answer++;
        }

        return answer;
    }
}