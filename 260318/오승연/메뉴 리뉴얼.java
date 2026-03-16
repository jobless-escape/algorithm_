import java.io.*;
import java.util.*;

class Solution {
    static HashMap<String,Integer> map=new HashMap<>();
    static ArrayList<String> list=new ArrayList<>();
    
    public String[] solution(String[] orders, int[] course) {        
        for(int i:course){
            for(int j=0;j<orders.length;j++){
                char[] ch=orders[j].toCharArray();
                Arrays.sort(ch);
                dfs(0,"",ch,i);
            }
            if(!map.isEmpty()){
                List<Integer> valuelist=new ArrayList<>(map.values());
                int max=Collections.max(valuelist);
                
                if(max>1){
                    for(String key:map.keySet()){
                        if(map.get(key)==max){
                            list.add(key);
                        }
                    }
                }
            }
            map.clear();
        }        
        
        Collections.sort(list);
        String[] answer= new String[list.size()];
        
        for(int i=0;i<list.size();i++){
            answer[i]=list.get(i);
        }
        
        return answer;
    }
    
    static void dfs(int depth, String chars, char[] ch, int i){
        if(chars.length()==i){
            map.put(chars, map.getOrDefault(chars,0)+1);
            return;
        }
        if(depth>=ch.length){
            return;
        }
        dfs(depth+1,chars+ch[depth],ch,i);
        dfs(depth+1,chars,ch,i);
    }
}
