import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        int[] answer = {};
        Map<String, Integer> map = new HashMap<>();

        for (String t : terms) {
            String[] sp = t.split(" ");
            map.put(sp[0], Integer.parseInt(sp[1]));
        }

        int todayNum = toDay(today);
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < privacies.length; i++) {
            String[] sp = privacies[i].split(" ");
            String date = sp[0];
            String type = sp[1];

            int start = toDay(date);
            int expire = start + (map.get(type) * 28) - 1;

            if (expire < todayNum) {
                result.add(i + 1);
            }
        }
        
        answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++) answer[i] = result.get(i);

        return answer;
    }

    // 날짜 -> 숫자 변환
    private int toDay(String date) {
        String[] sp = date.split("\\.");
        int y = Integer.parseInt(sp[0]);
        int m = Integer.parseInt(sp[1]);
        int d = Integer.parseInt(sp[2]);

        return (y * 12 * 28) + (m * 28) + d;
    }
}