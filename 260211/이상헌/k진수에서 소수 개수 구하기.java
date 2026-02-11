class Solution {
    public int solution(int n, int k) {
        int answer = 0;

        String converted = Integer.toString(n, k);

        String[] tokens = converted.split("0");

        for (String token : tokens) {
            if (token.equals("")) continue;

            long num = Long.parseLong(token);

            if (isPrime(num)) {
                answer++;
            }
        }

        return answer;
    }

    private boolean isPrime(long num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;

        for (long i = 3; i * i <= num; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
}