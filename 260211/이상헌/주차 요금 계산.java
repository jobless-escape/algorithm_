import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {

        Map<String, Integer> inTime = new HashMap<>();      1
        Map<String, Integer> totalTime = new TreeMap<>();

        for (String record : records) {
            String[] parts = record.split(" ");
            int time = toMinutes(parts[0]);
            String carNum = parts[1];
            String status = parts[2];

            if (status.equals("IN")) {
                inTime.put(carNum, time);
                totalTime.putIfAbsent(carNum, 0);
            } else {
                int parked = time - inTime.get(carNum);
                totalTime.put(carNum, totalTime.get(carNum) + parked);
                inTime.remove(carNum);
            }
        }

        int endOfDay = toMinutes("23:59");
        for (String carNum : inTime.keySet()) {
            int parked = endOfDay - inTime.get(carNum);
            totalTime.put(carNum, totalTime.get(carNum) + parked);
        }

        int basicTime = fees[0];
        int basicFee  = fees[1];
        int unitTime  = fees[2];
        int unitFee   = fees[3];

        int[] answer = new int[totalTime.size()];
        int idx = 0;

        for (int time : totalTime.values()) {
            if (time <= basicTime) {
                answer[idx++] = basicFee;
            } else {
                int over = time - basicTime;
                answer[idx++] = basicFee + (int) Math.ceil((double) over / unitTime) * unitFee;
            }
        }

        return answer;
    }

    private int toMinutes(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
}