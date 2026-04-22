class Solution {
    static boolean[] visited;
    static int[][] computers;

    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        Solution.computers = computers;
        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, n);
                answer++;
            }
        }

        return answer;
    }

    static void dfs(int node, int n) {
        visited[node] = true;

        for (int next = 0; next < n; next++) {
            if (node != next && computers[node][next] == 1 && !visited[next]) {
                dfs(next, n);
            }
        }
    }
}