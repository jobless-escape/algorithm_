import java.util.*;

class Solution {
    List<String> answer = new ArrayList<>();
    boolean found = false;

    public String[] solution(String[][] tickets) {

        Arrays.sort(tickets, (a, b) -> {
            if (a[0].equals(b[0])) {
                return a[1].compareTo(b[1]);
            }
            return a[0].compareTo(b[0]);
        });

        boolean[] visited = new boolean[tickets.length];

        List<String> path = new ArrayList<>();
        path.add("ICN");

        dfs(tickets, visited, path);

        return answer.toArray(new String[0]);
    }

    private void dfs(String[][] tickets, boolean[] visited, List<String> path) {
        // 모든 티켓을 사용했으면 (경로 길이 = 티켓 수 + 1)
        if (path.size() == tickets.length + 1) {
            answer = new ArrayList<>(path);
            found = true;
            return;
        }


        String current = path.get(path.size() - 1);

        // 모든 티켓 확인
        for (int i = 0; i < tickets.length; i++) {
            // 이미 사용한 티켓이면 스킵
            if (visited[i]) continue;

            if (tickets[i][0].equals(current)) {
                visited[i] = true;
                path.add(tickets[i][1]);

                dfs(tickets, visited, path);

                if (found) return;

                // 백트래킹
                visited[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}