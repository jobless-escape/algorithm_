import java.util.*;

class Solution {
	List<int[]> result = new ArrayList<>();

	public int[][] solution(int n) {
		hanoi(n, 1, 3);
		return result.toArray(new int[0][]);
	}

	void hanoi(int n, int from, int to) {
		if (n == 1) {
			result.add(new int[]{from, to});
			return;
		}
		int via = 6 - from - to;
		hanoi(n - 1, from, via);
		result.add(new int[]{from, to});
		hanoi(n - 1, via, to);
	}
}