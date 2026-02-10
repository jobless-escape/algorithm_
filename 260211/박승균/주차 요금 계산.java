import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        int[] answer;
        HashMap<String, Integer> inTime = new HashMap<>();
        HashMap<String, Integer> totalTime = new HashMap<>();
        
        for (String record : records) {
            String[] parts = record.split(" ");
            int time = toMin(parts[0]);
            String car = parts[1];
            String type = parts[2];

            if (type.equals("IN")) {
                inTime.put(car, time);
            } else {
                int diff = time - inTime.get(car);
                totalTime.put(car, totalTime.getOrDefault(car, 0) + diff);
                inTime.remove(car);
            }
        }
        
        int end = toMin("23:59");
        for (String car : inTime.keySet()) {
            int diff = end - inTime.get(car);
            totalTime.put(car, totalTime.getOrDefault(car, 0) + diff);
        }
        
        List<String> cars = new ArrayList<>(totalTime.keySet());
        Collections.sort(cars);
        answer = new int[cars.size()];
        for(int i = 0; i < answer.length; i++) 
            answer[i] = calcFee(totalTime.get(cars.get(i)), fees);
        
        return answer;
    }
    
    int toMin(String time) {
        String[] t = time.split(":");
        return Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
    }
    
    int calcFee(int time, int[] fees) {
        if (time <= fees[0]) return fees[1];
        
        int extra = time - fees[0];
        return fees[1] +
            (int)Math.ceil(extra / (double)fees[2]) * fees[3];
    }
}