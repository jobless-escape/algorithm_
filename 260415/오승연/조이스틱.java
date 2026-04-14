import java.io.*;
import java.util.*;

class Solution {    
    public int solution(String name) {
        int answer = 0;
        
        //위아래키
        for(int i=0;i<name.length();i++){
            int diff=name.charAt(i)-'A';
            answer+=Math.min(diff, 26-diff);
        }
        
        //좌우키
        int move=name.length()-1; //오른쪽으로 쭉 이동했을때
        for(int i=0;i<name.length();i++){
            int next=i+1;
            
            while(next<name.length() && name.charAt(next)=='A'){
                next++; //연속된 A덩어리까지 이동
            }
            
            move=Math.min(move, i*2+name.length()-next);//오른쪽갔다가 왼쪽
            move=Math.min(move, (name.length()-next)*2+i);//왼쪽갔다가 오른쪽
        }
        
        answer+=move;
        
        return answer;
    }
}
