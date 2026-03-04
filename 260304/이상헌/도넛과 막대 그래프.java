import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        // 노드 번호의 최댓값 파악
        int maxNode = 0;
        for (int[] edge : edges) {
            maxNode = Math.max(maxNode, Math.max(edge[0], edge[1]));
        }

        int[] inDegree = new int[maxNode + 1];   // 진입차수
        int[] outDegree = new int[maxNode + 1];  // 진출차수

        // 진입/진출 차수 계산
        for (int[] edge : edges) {
            outDegree[edge[0]]++;
            inDegree[edge[1]]++;
        }

        // 생성된 노드 찾기: 진입차수 == 0 && 진출차수 >= 2
        int generatedNode = -1;
        for (int i = 1; i <= maxNode; i++) {
            if (inDegree[i] == 0 && outDegree[i] >= 2) {
                generatedNode = i;
                break;
            }
        }

        // 인접 리스트 구성
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
        }

        int donut = 0, bar = 0, eight = 0;

        // 생성된 노드에서 연결된 각 그래프 판별
        for (int next : graph.getOrDefault(generatedNode, new ArrayList<>())) {
            int[] result = classifyGraph(next, generatedNode, inDegree, outDegree, graph);
            donut += result[0];
            bar += result[1];
            eight += result[2];
        }

        return new int[]{generatedNode, donut, bar, eight};
    }

    // BFS로 연결된 그래프 탐색 및 분류
    private int[] classifyGraph(int start, int generatedNode,
                                int[] inDegree, int[] outDegree,
                                Map<Integer, List<Integer>> graph) {
        boolean hasZeroOut = false;   // 진출차수 0인 노드 (막대)
        boolean hasBothGe2 = false;  // 진입/진출 모두 >=2 (8자)

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.add(start);
        visited.add(start);
        visited.add(generatedNode);

        while (!queue.isEmpty()) {
            int cur = queue.poll();

            if (outDegree[cur] == 0) hasZeroOut = true;
            if (inDegree[cur] >= 2 && outDegree[cur] >= 2) hasBothGe2 = true;

            for (int next : graph.getOrDefault(cur, new ArrayList<>())) {
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            }
        }

        if (hasZeroOut) return new int[]{0, 1, 0};      // 막대
        if (hasBothGe2) return new int[]{0, 0, 1};      // 8자
        return new int[]{1, 0, 0};                       // 도넛
    }
}