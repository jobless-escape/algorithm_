import java.util.*;

class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        int[] answer = new int[2];
        List<List<Integer>> adj;
        boolean[] visited;
        int n = nodes.length;
        int max = 0;
        for (int node : nodes) {
            max = Math.max(max, node);
        }

        // 인접 리스트 생성
        adj = new ArrayList<>();
        for (int i = 0; i <= max; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        visited = new boolean[max + 1]; // 1 - based

        for (int node : nodes) {

            if (!visited[node]) {
                int forward = 0;
                int reverse = 0;

                Queue<Integer> q = new LinkedList<>();
                q.offer(node);
                visited[node] = true;

                // 트리 전체 탐색
                while (!q.isEmpty()) {
                    int cur = q.poll();

                    int degree = adj.get(cur).size();
                    int childCount = degree - 1; // 부모가 있다고 가정

                    if (cur % 2 == childCount % 2) 
                        forward++;
                    else 
                        reverse++;

                    for (int next : adj.get(cur)) {
                        if (!visited[next]) {
                            visited[next] = true;
                            q.offer(next);
                        }
                    }
                }

                if (reverse == 1) answer[0]++;
                if (forward == 1) answer[1]++;
            }
        }

        return answer;
    }
}