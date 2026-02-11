import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {

        int baseTime = fees[0];
        int baseFee = fees[1];
        int addTime = fees[2];
        int addFee = fees[3];

        Map<String, Integer> inTime = new HashMap<>();
        TreeMap<String, Integer> totalTime = new TreeMap<>();

        for (String record : records) {
            String[] parts = record.split(" ");
            int time = toMinute(parts[0]);
            String car = parts[1];
            String type = parts[2];

            if (type.equals("IN")) {
                inTime.put(car, time);
            } else {
                int duration = time - inTime.get(car);
                totalTime.put(car,
                        totalTime.getOrDefault(car, 0) + duration);
                inTime.remove(car);
            }
        }

        int endTime = 23 * 60 + 59;

        for (String car : inTime.keySet()) {
            int duration = endTime - inTime.get(car);
            totalTime.put(car,
                    totalTime.getOrDefault(car, 0) + duration);
        }

        int[] answer = new int[totalTime.size()];
        int idx = 0;

        for (int time : totalTime.values()) {

            if (time <= baseTime) {
                answer[idx++] = baseFee;
            } else {
                int extra = time - baseTime;
                int extraFee =
                        ((extra + addTime - 1) / addTime) * addFee;
                answer[idx++] = baseFee + extraFee;
            }
        }

        return answer;
    }

    private int toMinute(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60
             + Integer.parseInt(t[1]);
    }
}