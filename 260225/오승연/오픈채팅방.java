import java.io.*;
import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        HashMap<String,String> map=new HashMap();
        List<String> EorL=new ArrayList<>();
        List<String> uids=new ArrayList<>();
        
        for(String s:record){
            String[] str=s.split(" ");
            String first=str[0];
            String uid=str[1];
            
            if(first.equals("Enter")){
                EorL.add("님이 들어왔습니다.");
                uids.add(uid);
                if(!map.containsKey(uid)){
                    map.put(uid,str[2]);
                }else{
                    if(!(map.get(uid)).equals(str[2])){
                        map.remove(uid);
                        map.put(uid,str[2]);
                    }
                }
            }else if(first.equals("Leave")){
                EorL.add("님이 나갔습니다.");
                uids.add(uid);
            }else{ //Change
                map.remove(uid);
                map.put(uid,str[2]);
            }
        }
        
        String[] answer=new String[EorL.size()];
        for(int i=0;i<EorL.size();i++){
            answer[i]=map.get(uids.get(i))+EorL.get(i);
        }
        
        return answer;
    }
}
