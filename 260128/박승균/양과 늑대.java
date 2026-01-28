import java.util.*;

class Solution {
    static List<Integer>[] tree;
    static int[] info;
    static int maxSheep = 0;

    public int solution(int[] info, int[][] edges) {
        this.info = info;

        tree = new ArrayList[info.length];
        for (int i = 0; i < info.length; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            tree[e[0]].add(e[1]);
        }

        List<Integer> candidates = new ArrayList<>();
        candidates.add(0);

        dfs(0, 0, 0, candidates);
        return maxSheep;
    }

    void dfs(int node, int sheep, int wolf, List<Integer> candidates) {
        if (info[node] == 0) sheep++;
        else wolf++;

        if (wolf >= sheep) return;

        maxSheep = Math.max(maxSheep, sheep);

        List<Integer> nextCandidates = new ArrayList<>(candidates);
        nextCandidates.remove(Integer.valueOf(node));
        nextCandidates.addAll(tree[node]);

        for (int next : nextCandidates) {
            dfs(next, sheep, wolf, nextCandidates);
        }
    }
}
