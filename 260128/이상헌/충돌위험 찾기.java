import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        List<List<int[]>> allPaths = new ArrayList<>();

        for (int[] route : routes) {
            List<int[]> path = new ArrayList<>();

            for (int i = 0; i < route.length - 1; i++) {
                int[] start = points[route[i] - 1];
                int[] end = points[route[i + 1] - 1];

                if (i == 0) {
                    path.add(new int[]{start[0], start[1]});
                }

                int r = start[0], c = start[1];
                while (r != end[0]) {
                    r += (r < end[0]) ? 1 : -1;
                    path.add(new int[]{r, c});
                }

                while (c != end[1]) {
                    c += (c < end[1]) ? 1 : -1;
                    path.add(new int[]{r, c});
                }
            }

            allPaths.add(path);
        }

        int maxTime = 0;
        for (List<int[]> path : allPaths) {
            maxTime = Math.max(maxTime, path.size());
        }

        int collisions = 0;
        for (int t = 0; t < maxTime; t++) {
            Map<String, Integer> positionCount = new HashMap<>();

            for (List<int[]> path : allPaths) {
                if (t < path.size()) {
                    int[] pos = path.get(t);
                    String key = pos[0] + "," + pos[1];
                    positionCount.put(key, positionCount.getOrDefault(key, 0) + 1);
                }
            }

            for (int count : positionCount.values()) {
                if (count > 1) {
                    collisions++;
                }
            }
        }

        return collisions;
    }
}