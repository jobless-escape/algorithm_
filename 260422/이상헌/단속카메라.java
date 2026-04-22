import java.util.Arrays;

class Solution {
    public int solution(int[][] routes) {
        Arrays.sort(routes, (a, b) -> a[1] - b[1]);

        int count = 1;
        int camera = routes[0][1];

        for (int i = 1; i < routes.length; i++) {
            if (camera < routes[i][0]) {
                count++;
                camera = routes[i][1];
            }
        }

        return count;
    }
}