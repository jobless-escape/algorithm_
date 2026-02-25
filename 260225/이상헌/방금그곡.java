import java.util.*;

class Solution {
    public String solution(String m, String[] musicinfos) {
        String melody = sharp(m);

        String answer = "(None)";
        int maxTime = 0;

        for (String info : musicinfos) {
            String[] parts = info.split(",");
            int start = toMin(parts[0]);
            int end = toMin(parts[1]);
            String title = parts[2];
            String sheet = sharp(parts[3]);

            int playTime = end - start;

            String played = makeSheet(sheet, playTime);

            if (played.contains(melody)) {
                if (playTime > maxTime) {
                    maxTime = playTime;
                    answer = title;
                }
            }
        }

        return answer;
    }

    private int toMin(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }

    private String sharp(String s) {
        s = s.replace("C#", "c");
        s = s.replace("D#", "d");
        s = s.replace("E#", "e");
        s = s.replace("F#", "f");
        s = s.replace("G#", "g");
        s = s.replace("A#", "a");
        s = s.replace("B#", "b");
        return s;
    }

    private String makeSheet(String sheet, int time) {
        StringBuilder sb = new StringBuilder();
        int len = sheet.length();
        for (int i = 0; i < time; i++) {
            sb.append(sheet.charAt(i % len));
        }
        return sb.toString();
    }
}