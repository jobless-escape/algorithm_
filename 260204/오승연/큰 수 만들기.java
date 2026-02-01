import java.io.*;
import java.util.*;

class Solution {
    public String solution(String number, int k) {
        StringBuilder answer = new StringBuilder();
        List<Integer> arr = new ArrayList<>();

        for (int i = 0; i < number.length(); i++) {
            arr.add(number.charAt(i) - '0');
        }
        
        int idx=0;
        while(k>0){
            if(arr.get(idx)<arr.get(idx+1)){
                arr.remove(idx);
                k--;
            }else if(arr.get(idx)>arr.get(idx+1)){
                arr.remove(idx+1);
                k--;
            }else{
                idx++;
            }
        }
        
        for(int i:arr){
            answer.append(i);
        }
        
        return answer.toString();
    }
}
