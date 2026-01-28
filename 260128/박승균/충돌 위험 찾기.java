import java.util.*;

class Robot {
    int x, y;
    int routeIdx;
    int id;

    Robot(int x, int y, int routeIdx, int id) {
        this.x = x;
        this.y = y;
        this.routeIdx = routeIdx;
        this.id = id;
    }
}

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        // 1 - based
        int[][] p = new int[points.length + 1][2];
        for (int i = 0; i < points.length; i++) {
            p[i + 1][0] = points[i][0];
            p[i + 1][1] = points[i][1];
        }

        Queue<Robot> q = new LinkedList<>();

        for (int i = 0; i < routes.length; i++) {
            int start = routes[i][0];
            q.offer(new Robot(p[start][0], p[start][1], 1, i));
        }

        while (!q.isEmpty()) {
            int size = q.size();
            Map<String, Integer> posCount = new HashMap<>();

            for (int i = 0; i < size; i++) {
                Robot r = q.poll();

                String key = r.x + "," + r.y;
                posCount.put(key, posCount.getOrDefault(key, 0) + 1);

                if (r.routeIdx < routes[r.id].length) {
                    int target = routes[r.id][r.routeIdx];
                    int tx = p[target][0];
                    int ty = p[target][1];

                    if (r.x != tx) {
                        r.x += (tx > r.x) ? 1 : -1;
                    } else if (r.y != ty) {
                        r.y += (ty > r.y) ? 1 : -1;
                    }

                    if (r.x == tx && r.y == ty) {
                        r.routeIdx++;
                    }

                    q.offer(r);
                }
            }

            for (int cnt : posCount.values()) {
                if (cnt >= 2) answer++;
            }
        }

        return answer;
    }
}
