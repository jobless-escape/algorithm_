import java.io.*;
import java.util.*;

class Solution {
    public List<Integer> solution(String s) {
        List<Integer> answer = new ArrayList<>();
        s=s.substring(2,s.length()-2).replace("},{","-");
        String[] str=s.split("-");
        
        Arrays.sort(str,
            (a,b)->Integer.compare(a.length(),b.length())
        );
        
        for(String st:str){
            String[] temp=st.split(",");
            for(String t:temp){
                int a=Integer.parseInt(t);
                if(!answer.contains(a)){
                    answer.add(a);
                }
            }
        }
        return answer;
    }
}
