import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int[] answer = new int[2];
        
        HashSet<String> set=new HashSet<>();
        for(String gem:gems){
            set.add(gem);
        }
        int total=set.size();
        
        int start=0;
        int end=0;
        int len=100001;
        HashMap<String,Integer> map=new HashMap<>();
        while(end<gems.length){
            map.put(gems[end],map.getOrDefault(gems[end],0)+1);
            
            while(map.size()==total){
                if(end-start<len){
                    len=end-start;
                    answer[0]=start+1;
                    answer[1]=end+1;
                }
                
                map.put(gems[start],map.get(gems[start])-1);
                if(map.get(gems[start])==0) map.remove(gems[start]);
                
                start++;
            }
            
            end++;
        }
        
        return answer;
    }
}
