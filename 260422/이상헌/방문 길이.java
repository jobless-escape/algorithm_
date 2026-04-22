import java.util.HashSet;

class Solution {
    public int solution(String dirs) {
        HashSet<String> visited = new HashSet<>();

        int x = 0, y = 0;

        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0};

        for (char c : dirs.toCharArray()) {
            int dir = "UDLR".indexOf(c);

            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if (nx < -5 || nx > 5 || ny < -5 || ny > 5) continue;

            visited.add(x + "" + y + "" + nx + "" + ny);
            visited.add(nx + "" + ny + "" + x + "" + y);

            x = nx;
            y = ny;
        }

        return visited.size() / 2;
    }
}