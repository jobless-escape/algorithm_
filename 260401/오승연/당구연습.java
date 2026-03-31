import java.util.*;

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

		for (int i = 0; i < balls.length; i++) {
			int targetX = balls[i][0];
			int targetY = balls[i][1];

			List<Integer> whatismin=new ArrayList<>();

			// 좌
			if (!(startY == targetY && startX >= targetX)) {
				whatismin.add(getDistance(startX, startY, targetX * (-1), targetY));
			}

			// 우
			if (!(startY == targetY && startX <= targetX)) {
				whatismin.add(getDistance(startX, startY, m + (m - targetX), targetY));
			}

			// 상
			if (!(startX == targetX && startY <= targetY)) {
				whatismin.add(getDistance(startX, startY, targetX, n + (n - targetY)));
			}

			// 하
			if (!(startX == targetX && startY >= targetY)) {
				whatismin.add(getDistance(startX, startY, targetX, targetY * (-1)));
			}
            
            answer[i] = Collections.min(whatismin);
		}

		return answer;
    }
    
    public int getDistance(int sx, int sy, int tx, int ty) {
		return (int) (Math.pow(sx - tx, 2) + Math.pow(sy - ty, 2));
	}
}
