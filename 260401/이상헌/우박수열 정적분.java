class Solution {
    public double[] solution(int k, int[][] ranges) {

        java.util.List<Long> seq = new java.util.ArrayList<>();
        seq.add((long) k);

        while (k != 1) {
            if (k % 2 == 0) k /= 2;
            else k = k * 3 + 1;
            seq.add((long) k);
        }

        int n = seq.size();

        double[] prefix = new double[n];
        prefix[0] = 0;
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + (seq.get(i - 1) + seq.get(i)) / 2.0;
        }

        double[] answer = new double[ranges.length];
        for (int i = 0; i < ranges.length; i++) {
            int a = ranges[i][0];
            int b = (n - 1) + ranges[i][1];

            if (a > b) {
                answer[i] = -1;
            } else {
                answer[i] = prefix[b] - prefix[a];
            }
        }

        return answer;
    }
}