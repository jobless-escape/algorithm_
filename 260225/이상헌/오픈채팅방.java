import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> userMap = new HashMap<>();

        for (String log : record) {
            String[] parts = log.split(" ");
            String command = parts[0];
            String userId = parts[1];

            if (command.equals("Enter") || command.equals("Change")) {
                String nickname = parts[2];
                userMap.put(userId, nickname);
            }
        }

        ArrayList<String> result = new ArrayList<>();

        for (String log : record) {
            String[] parts = log.split(" ");
            String command = parts[0];
            String userId = parts[1];

            if (command.equals("Enter")) {
                result.add(userMap.get(userId) + "님이 들어왔습니다.");
            } else if (command.equals("Leave")) {
                result.add(userMap.get(userId) + "님이 나갔습니다.");
            }
        }

        return result.toArray(new String[0]);
    }
}