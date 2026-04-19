import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        PriorityQueue<Integer> crews = new PriorityQueue<>();
        List<List<Integer>> bus = new ArrayList<>();
        
        for(int i = 0 ; i < n ; i++) bus.add(new ArrayList<>());
        
        for(String time : timetable) crews.add(stringToTime(time));
        
        int busTime = 9 * 60;
        int ans = 0;
        for(int i = 0 ; i < n ; i++){
            while(!crews.isEmpty()){
                int crew = crews.poll();
                
                if(bus.get(i).size() < m && crew <= busTime){
                    bus.get(i).add(crew);
                    ans = crew - 1;
                }
                else{
                    crews.add(crew);
                    break;
                }
            }
            busTime += t;
        }

        if(bus.get(n-1).size() < m){
            ans = busTime - t;
        }
        
        return timeToString(ans);
    }
    
    public String timeToString(int time){
        int hour = time / 60;
        int min = time % 60;
        String hourText;
        String minText;
        
        if(hour < 10){
            hourText = "0" + String.valueOf(hour);
        }
        else hourText = String.valueOf(hour);
        
        if(min < 10){
            minText = "0" + String.valueOf(min);
        }
        else minText = String.valueOf(min);
        
        return hourText + ":" + minText;
    }
    
    public int stringToTime(String time){
        int num = 0;
        String[] timeSplit = time.split(":");
        num += Integer.parseInt(timeSplit[0]) * 60;
        num += Integer.parseInt(timeSplit[1]);
        
        return num;
    }
}