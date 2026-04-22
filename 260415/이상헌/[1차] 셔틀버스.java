import java.util.Arrays;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        int[] times = new int[timetable.length];
        for (int i = 0; i < timetable.length; i++) {
            String[] split = timetable[i].split(":");
            times[i] = Integer.parseInt(split[0]) * 60 + Integer.parseInt(split[1]);
        }
        Arrays.sort(times);

        int idx = 0;
        int lastPerson = -1;
        int count = 0;

        for (int i = 0; i < n; i++) {
            int busTime = 540 + t * i;
            count = 0;
            while (idx < times.length && count < m && times[idx] <= busTime) {
                lastPerson = times[idx];
                idx++;
                count++;
            }
        }

        int answer;
        if (count < m) {
            answer = 540 + t * (n - 1);
        } else {
            answer = lastPerson - 1;
        }

        return String.format("%02d:%02d", answer / 60, answer % 60);
    }
}