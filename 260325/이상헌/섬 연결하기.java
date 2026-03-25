import java.util.*;

class Solution {
    static int[] parent;

    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    static boolean union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA == rootB) return false;
        parent[rootA] = rootB;
        return true;
    }

    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);

        parent = new int[n];
        for (int i = 0; i < n; i++) parent[i] = i;

        int answer = 0;
        int edgeCount = 0;

        for (int[] cost : costs) {
            if (union(cost[0], cost[1])) {
                answer += cost[2];
                edgeCount++;
                if (edgeCount == n - 1) break;
            }
        }

        return answer;
    }
}