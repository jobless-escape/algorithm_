class Solution {
	public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
		int[] answer = new int[balls.length];

		for (int i = 0; i < balls.length; i++) {
			int tx = balls[i][0];
			int ty = balls[i][1];
			long minDist = Long.MAX_VALUE;

			if (!(startX == tx && startY < ty)) {
				minDist = Math.min(minDist, getDist(startX, startY, tx, 2 * n - ty));
			}

			if (!(startX == tx && startY > ty)) {
				minDist = Math.min(minDist, getDist(startX, startY, tx, -ty));
			}

			if (!(startY == ty && startX < tx)) {
				minDist = Math.min(minDist, getDist(startX, startY, 2 * m - tx, ty));
			}

			if (!(startY == ty && startX > tx)) {
				minDist = Math.min(minDist, getDist(startX, startY, -tx, ty));
			}

			answer[i] = (int) minDist;
		}

		return answer;
	}

	private long getDist(long x1, long y1, long x2, long y2) {
		return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
	}
}