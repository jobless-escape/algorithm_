import java.util.*;

class Solution {
    public int solution(String[][] relation) {
        int rowCount = relation.length;
        int colCount = relation[0].length;

        List<Integer> candidateKeys = new ArrayList<>();

        for (int mask = 1; mask < (1 << colCount); mask++) {

            if (!isUnique(relation, mask, rowCount, colCount)) continue;

            if (!isMinimal(candidateKeys, mask)) continue;

            candidateKeys.add(mask);
        }

        return candidateKeys.size();
    }

    private boolean isUnique(String[][] relation, int mask, int rowCount, int colCount) {
        Set<String> seen = new HashSet<>();

        for (int row = 0; row < rowCount; row++) {
            StringBuilder key = new StringBuilder();

            for (int col = 0; col < colCount; col++) {
                if ((mask >> col & 1) == 1) {
                    key.append(relation[row][col]).append(",");
                }
            }

            if (!seen.add(key.toString())) return false;
        }

        return true;
    }

    private boolean isMinimal(List<Integer> candidateKeys, int mask) {
        for (int ck : candidateKeys) {
            if ((ck & mask) == ck) return false;
        }
        return true;
    }
}