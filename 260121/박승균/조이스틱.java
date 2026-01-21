class Solution {
    public int solution(String name) {
        int answer = 0;
        int len = name.length();

        // 각각 알파벳 맞추기 비용
        for (int i = 0; i < len; i++) {
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);
        }

        // 포인터 비용
        int move = len - 1;

        for (int i = 0; i < len; i++) {
            // i는 꺾는 지점
            int next = i + 1; // index = i 다음으로 A 가 아닌 지점

            while (next < len && name.charAt(next) == 'A') {
                next++;
            }

            move = Math.min(move, i * 2 + len - next); // ->, <-
            move = Math.min(move, (len - next) * 2 + i); // <-, ->
        }

        return answer + move;
    }
}
