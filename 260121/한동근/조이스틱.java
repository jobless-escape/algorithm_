class Solution {
    public int solution(String name) {
        int answer = 0;
        int n = name.length();

        for(int i = 0 ; i < n ; i++){
            char alphabet = name.charAt(i);

            int up = alphabet - 'A';
            int down = 26 - up;
            answer += Math.min(up, down);
        }

        int move = n - 1; // 오른쪽으로 쭉 가는 경우
        for(int i = 0 ; i < n ; i++){
            int next = i + 1;

            while(next < n && name.charAt(next) == 'A') next++;

            int case1 = (i * 2) + n - next; // 오른쪽으로 가다가 꺾어서 가는 경우
            int case2 = (n - next) * 2 + i; // 왼쪽으로 가다가 꺾어서 다시 오는 경우
            move = Math.min(move, Math.min(case1, case2));
        }

        return answer + move;
    }
}