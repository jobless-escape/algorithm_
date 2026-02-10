class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String s = Integer.toString(n, k);
        String[] arr = s.split("0");
        
        for(String str : arr) {
            if(str.isEmpty()) continue;

            long num = Long.parseLong(str);

            if(isPrime(num)) answer++;
        }
        
        return answer;
    }
    
    boolean isPrime(long num) {
        if(num < 2) return false;

        for(long i = 2; i * i <= num; i++) {
            if(num % i == 0) return false;
        }
        return true;
    }
}