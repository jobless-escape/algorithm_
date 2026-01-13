import java.util.*;

class Solution {

    String[][] tickets;
    boolean[] visited;
    List<String> path = new ArrayList<>();
    String[] answer;
    boolean finished = false;

    public String[] solution(String[][] tickets) {
        this.tickets = tickets;

        Arrays.sort(this.tickets, (a, b) -> { //미리 정렬
            if (a[0].equals(b[0])) {
                return a[1].compareTo(b[1]);
            }
            return a[0].compareTo(b[0]);
        });

        visited = new boolean[this.tickets.length];

        path.add("ICN");
        dfs("ICN", 0);

        return answer;
    }

    private void dfs(String current, int usedCount) {
        if (usedCount == tickets.length) { //다쓰면
            answer = path.toArray(new String[0]);
            finished = true;
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(current)) {

                visited[i] = true;
                path.add(tickets[i][1]);

                dfs(tickets[i][1], usedCount + 1);

                if (finished) return;

                visited[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
