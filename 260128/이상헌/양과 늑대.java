import java.util.*;

class Solution {
    static int maxSheep;
    static int[] info;
    static ArrayList<Integer>[] tree;

    public int solution(int[] info, int[][] edges) {
        this.info = info;
        maxSheep = 0;

        tree = new ArrayList[info.length];
        for (int i = 0; i < info.length; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            tree[edge[0]].add(edge[1]);
        }

        List<Integer> nextNodes = new ArrayList<>();
        nextNodes.add(0);
        dfs(0, 0, 0, nextNodes);

        return maxSheep;
    }

    void dfs(int current, int sheep, int wolf, List<Integer> nextNodes) {
        if (info[current] == 0) {
            sheep++;
        } else {
            wolf++;
        }

        if (wolf >= sheep) {
            return;
        }

        maxSheep = Math.max(maxSheep, sheep);

        List<Integer> newNext = new ArrayList<>(nextNodes);
        newNext.remove(Integer.valueOf(current));
        newNext.addAll(tree[current]);

        for (int nextNode : newNext) {
            dfs(nextNode, sheep, wolf, newNext);
        }
    }
}