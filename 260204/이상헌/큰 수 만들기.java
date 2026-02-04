import java.util.*;

class Solution {
    public String solution(String number, int k) {
        Deque<Character> deque = new ArrayDeque<>();
        int removeCount = 0;

        for (int i = 0; i < number.length(); i++) {
            char current = number.charAt(i);

            while (!deque.isEmpty() && deque.peekLast() < current && removeCount < k) {
                deque.removeLast();
                removeCount++;
            }

            deque.addLast(current);
        }

        while (removeCount < k) {
            deque.removeLast();
            removeCount++;
        }

        StringBuilder answer = new StringBuilder();
        while (!deque.isEmpty()) {
            answer.append(deque.removeFirst());
        }

        return answer.toString();
    }
}