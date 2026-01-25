import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[] money) {
        int answer = 0;
        int len = money.length;
        
        int[] first=new int[len];
        int[] second=new int[len];
        
        first[0]=first[1]=money[0];
        second[1]=money[1];
        
        for(int i=2;i<len;i++){
            first[i]=Math.max(first[i-1],first[i-2]+money[i]);
            second[i]=Math.max(second[i-1],second[i-2]+money[i]);
        }
        
        answer=Math.max(first[len-2], second[len-1]);
        return answer;
    }
}
