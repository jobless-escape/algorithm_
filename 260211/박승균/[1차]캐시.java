import java.util.*;

class Solution {
    static List<String> list = new ArrayList<>();
    
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        for(String str: cities){
            String st = str.toLowerCase();
            int idx = hasString(st);
            if(idx != -1){ // 캐시 히트
                list.remove(idx);
                list.add(st);
                answer += 1;
            }
            else { // 캐시 미스
                list.add(st);
                answer += 5;
            }
            
            if(list.size() > cacheSize){
                list.remove(0);
            }
        }
        
        
        return answer;
    }
    
    public int hasString(String st){
        for(int i = 0; i < list.size(); i++){
            String s = list.get(i);
            if(s.equals(st)){
                return i;
            }
        }
        return -1;
    }
}