import java.util.*;
import java.io.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String s = Integer.toString(n, k);
        String[] arr=s.split("0");
        
        for(String st:arr){
            // System.out.println(st);
            if(st.equals("")) continue;
            Long l=Long.parseLong(st);
            if(isPrime(l)){
                answer++;
            }
        }
        return answer;
    }
    static boolean isPrime(Long l){
        if(l<=1) return false;
        
        for(int i=2;i<=(int)Math.sqrt(l);i++){
            if(l%i==0) return false;
        }
        
        return true;
    }
}
