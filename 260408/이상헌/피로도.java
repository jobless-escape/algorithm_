class Solution {
    static boolean[] visited;
    static int answer = 0;

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dfs(k, 0, dungeons);
        return answer;
    }

    static void dfs(int currentHp, int count, int[][] dungeons) {
        answer = Math.max(answer, count);

        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && currentHp >= dungeons[i][0]) {
                visited[i] = true;
                dfs(currentHp - dungeons[i][1], count + 1, dungeons);
                visited[i] = false;
            }
        }
    }
}