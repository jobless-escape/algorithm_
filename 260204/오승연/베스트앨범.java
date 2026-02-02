import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer=new ArrayList<>();
        
        HashMap<String,Integer> genre_cnt=new HashMap<>();
        HashMap<String,HashMap<Integer,Integer>> idx_play=new HashMap<>();
        
        for(int i=0;i<plays.length;i++){
            if(!genre_cnt.containsKey(genres[i])){
                HashMap<Integer,Integer> map=new HashMap<>();
                map.put(i,plays[i]);
                genre_cnt.put(genres[i],plays[i]);
                idx_play.put(genres[i],map);
            }else{
                genre_cnt.put(genres[i],genre_cnt.get(genres[i])+plays[i]);
                idx_play.get(genres[i]).put(i,plays[i]);
            }
        }
        
        List<String> genre_key=new ArrayList(genre_cnt.keySet());
        Collections.sort(genre_key, (s1,s2) -> genre_cnt.get(s2) - genre_cnt.get(s1));
        
        for(String key: genre_key){
            HashMap<Integer,Integer> map=idx_play.get(key);
            List<Integer> idx_key=new ArrayList(map.keySet());
            
            Collections.sort(idx_key, (s1,s2) -> map.get(s2) - map.get(s1));
            
            answer.add(idx_key.get(0));
            if(idx_key.size()>1){
                answer.add(idx_key.get(1));
            }
        }
        
        return answer.stream().mapToInt(i -> i).toArray();
    }
}
