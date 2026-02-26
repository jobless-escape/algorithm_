import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        m = convert(m);
        
        int maxTime = -1;
        
        for (String info : musicinfos) {
            String[] parts = info.split(",");
            
            int start = toMin(parts[0]);
            int end = toMin(parts[1]);
            int playTime = end - start;
            
            String title = parts[2];
            String melody = convert(parts[3]);
            
            // 실제 재생된 멜로디 생성
            StringBuilder sb = new StringBuilder();
            
            for (int i = 0; i < playTime; i++) {
                sb.append(melody.charAt(i % melody.length()));
            }
            
            String played = sb.toString();
            
            if (played.contains(m)) {
                if (playTime > maxTime) {
                    maxTime = playTime;
                    answer = title;
                }
            }
        }
        
        return answer;
    }
    
    static int toMin(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
    
    static String convert(String s) {
        StringBuilder res = new StringBuilder();
        
        for (int i = 0; i < s.length(); i++) {
            if (i + 1 < s.length() && s.charAt(i + 1) == '#') {
                res.append(Character.toLowerCase(s.charAt(i)));
                i++;
            } else {
                res.append(s.charAt(i));
            }
        }
        
        return res.toString();
    }
}