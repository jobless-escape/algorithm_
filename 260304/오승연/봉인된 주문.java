import java.io.*;
import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        String answer = "";
        
        long[] nums=new long[bans.length];
        for(int i=0; i<bans.length; i++) {
            nums[i] = convertnum(bans[i]);
        }
        
        Arrays.sort(nums);
        for(long nu:nums){
            if(nu<=n) n++;
        }
        
        answer=convertstr(n);
        
        return answer;
    }
    
    static long convertnum(String str){
        int length = str.length();
        long num = 0;

        for(int j=0;j<length;j++) {
            num += (str.charAt(j)-96) * Math.pow(26,(length-1-j));
        }

        return num;    
    }
    
    static String convertstr(long num){
        String str = "";

        while(num > 0) {
            str = String.valueOf((char)((num-1)%26+1+96)) + str;
            num=(num-1)/26;
        }

        return str;
    }
}
