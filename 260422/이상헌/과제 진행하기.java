import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        List<String> answer = new ArrayList<>();
        Deque<String[]> stack = new ArrayDeque<>();

        int n = plans.length;
        int[][] sorted = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] t = plans[i][1].split(":");
            sorted[i][0] = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
            sorted[i][1] = i;
        }
        Arrays.sort(sorted, (a, b) -> a[0] - b[0]);

        String[][] p = new String[n][3];
        for (int i = 0; i < n; i++) {
            p[i] = plans[sorted[i][1]];
        }

        int[] startMin = new int[n];
        for (int i = 0; i < n; i++) {
            String[] t = p[i][1].split(":");
            startMin[i] = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
        }

        for (int i = 0; i < n; i++) {
            String name = p[i][0];
            int playtime = Integer.parseInt(p[i][2]);

            if (i == n - 1) {
                answer.add(name);
                break;
            }

            int gap = startMin[i + 1] - startMin[i];

            if (gap >= playtime) {
                answer.add(name);
                int remain = gap - playtime;
                while (!stack.isEmpty() && remain > 0) {
                    String[] top = stack.peek();
                    int topTime = Integer.parseInt(top[1]);
                    if (remain >= topTime) {
                        remain -= topTime;
                        answer.add(top[0]);
                        stack.pop();
                    } else {
                        top[1] = String.valueOf(topTime - remain);
                        remain = 0;
                    }
                }
            } else {
                stack.push(new String[]{name, String.valueOf(playtime - gap)});
            }
        }

        while (!stack.isEmpty()) {
            answer.add(stack.pop()[0]);
        }

        return answer.toArray(new String[0]);
    }
}