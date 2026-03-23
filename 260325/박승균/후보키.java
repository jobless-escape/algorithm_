import java.util.*;

class Solution {

    String[][] relation;
    List<List<Integer>> candidateKeys = new ArrayList<>();

    public int solution(String[][] relation) {
        this.relation = relation;

        int col = relation[0].length;

        for (int size = 1; size <= col; size++) {
            dfs(new ArrayList<>(), 0, size);
        }

        return candidateKeys.size();
    }

    private void dfs(List<Integer> comb, int start, int targetSize) {

        if (comb.size() == targetSize) {
            for (List<Integer> key : candidateKeys) {
                if (comb.containsAll(key)) {
                    return;
                }
            }

            // 중복 체크
            Set<String> set = new HashSet<>();

            for (int i = 0; i < relation.length; i++) {
                StringBuilder sb = new StringBuilder();

                for (int col : comb) {
                    sb.append(relation[i][col]).append(",");
                }

                set.add(sb.toString());
            }

            if (set.size() == relation.length) {
                candidateKeys.add(new ArrayList<>(comb));
            }

            return;
        }

        for (int i = start; i < relation[0].length; i++) {
            comb.add(i);
            dfs(comb, i + 1, targetSize);
            comb.remove(comb.size() - 1);
        }
    }
}
