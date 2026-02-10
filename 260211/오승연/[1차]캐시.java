import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Queue<String> cache=new LinkedList<>();
        
        for(String st:cities){
            String s=st.toLowerCase();
            if(cache.contains(s)){
                cache.remove(s);
                cache.offer(s);
                answer++;
            }else{
                if(cache.size()==cacheSize){
                    cache.poll();
                }
                if(cache.size()<cacheSize){
                    cache.offer(s);
                }
                answer+=5;
            }
        }
        return answer;
    }
}
