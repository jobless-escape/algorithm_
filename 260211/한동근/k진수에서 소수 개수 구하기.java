class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String changeNum = Integer.toString(n, k);
        String[] parts = changeNum.split("0");
        
        for(String p : parts){
            if(p.isEmpty()) continue;
            
            long num = Long.parseLong(p);
            if(isPrime(num)) answer++;
        }
        return answer;
    }
    
    static boolean isPrime(long num){
        if(num < 2) return false;
        
        for(int i = 2 ; i <= Math.sqrt(num) ; i++){
            if(num % i == 0) return false;
        }
        return true;
    }
}