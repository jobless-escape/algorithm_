import java.io.*;
import java.util.*;

class Solution {
    public String solution(String s) {
        StringBuilder sb=new StringBuilder();
        boolean isFirst=true;
        
        for(int i=0;i<s.length();i++){
            char st=s.charAt(i);
            
            if(isFirst){
                sb.append(Character.toUpperCase(st));
            }else{
                sb.append(Character.toLowerCase(st));
            }
            
            if(st==' '){
                isFirst=true;
            }else{
                isFirst=false;
            }
        }
        return sb.toString();
    }
}
