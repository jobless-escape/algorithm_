class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        for (int h = 1; h * h <= yellow; h++) {
            if (yellow % h != 0) continue;

            int w = yellow / h;

            int width = w + 2;
            int height = h + 2;

            int count = width * height - yellow;

            if (count == brown) {
                answer[0] = Math.max(width, height);
                answer[1] = Math.min(width, height);
                return answer;
            }
        }
        return answer;
    }
}