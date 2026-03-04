import java.util.*;

class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        int n = 0;
        for (int node : nodes) n = Math.max(n, node);

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n + 1];
        int holJjak = 0, yeokHolJjak = 0;

        for (int start : nodes) {
            if (visited[start]) continue;

            Queue<Integer> queue = new LinkedList<>();
            queue.add(start);
            visited[start] = true;
            List<Integer> component = new ArrayList<>();

            while (!queue.isEmpty()) {
                int cur = queue.poll();
                component.add(cur);
                for (int next : graph.get(cur)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.add(next);
                    }
                }
            }

            int holViolate = 0, yeokViolate = 0;
            int holCandidate = -1, yeokCandidate = -1;

            for (int node : component) {
                int deg = graph.get(node).size();
                int childOddNonRoot = 1 - (deg % 2); // degree-1의 홀짝
                int nodeOdd = node % 2;

                if (nodeOdd != childOddNonRoot) { holViolate++;  holCandidate  = node; }
                if (nodeOdd == childOddNonRoot) { yeokViolate++; yeokCandidate = node; }
            }

            if (holViolate == 1) {
                int deg = graph.get(holCandidate).size();
                if (holCandidate % 2 == deg % 2) holJjak++;
            }

            if (yeokViolate == 1) {
                int deg = graph.get(yeokCandidate).size();
                if (yeokCandidate % 2 != deg % 2) yeokHolJjak++;
            }
        }

        return new int[]{holJjak, yeokHolJjak};
    }
}