import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        if(cacheSize == 0) return 5 * cities.length;
        
        int answer = 0;
        Queue<String> q = new ArrayDeque<>();
        
        for(String city : cities){
            city = city.toLowerCase();
            if(q.contains(city)){
                answer++;
                q.remove(city);
                q.offer(city);
            }
            else if(!q.contains(city)){
                if(q.size() < cacheSize) q.offer(city);
                else{
                    q.poll();
                    q.offer(city);
                }
                answer += 5;
            }
        }
        return answer;
    }
}