import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        for (int i = 0; i < s.length; i++) {
            answer[i] = solve(s[i]);
        }
        return answer;
    }

    private String solve(String str) {
        Deque<Character> stack = new ArrayDeque<>();
        int count = 0;

        for (char c : str.toCharArray()) {
            stack.push(c);

            if (stack.size() >= 3) {
                char top1 = stack.pop();
                char top2 = stack.pop();
                char top3 = stack.pop();

                if (top3 == '1' && top2 == '1' && top1 == '0') {
                    count++;
                } else {
                    stack.push(top3);
                    stack.push(top2);
                    stack.push(top1);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        String remaining = sb.toString();

        String toInsert = "110".repeat(count);

        int insertPos = remaining.length();
        while (insertPos > 0 && remaining.charAt(insertPos - 1) == '1') {
            insertPos--;
        }

        return remaining.substring(0, insertPos) + toInsert + remaining.substring(insertPos);
    }
}