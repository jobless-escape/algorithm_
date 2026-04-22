import java.util.*;

class Solution {
    public String[] solution(String[][] plans) {
        int n = plans.length;
        String[] answer = new String[n];
        
        int[][] plan = new int[n][3];
        Map<Integer, String> map = new HashMap<>();
        
        for (int i = 0; i < n; i++) {
            plan[i][0] = i;
            map.put(i, plans[i][0]);
            plan[i][1] = toMin(plans[i][1]);
            plan[i][2] = Integer.parseInt(plans[i][2]);
        }
        
        Arrays.sort(plan, (a, b) -> a[1] - b[1]);
        
        Stack<int[]> stack = new Stack<>(); // {id, 남은시간}
        List<Integer> endList = new ArrayList<>();
        
        for (int i = 0; i < n - 1; i++) {
            int id = plan[i][0];
            int start = plan[i][1];
            int duration = plan[i][2];
            
            int nextStart = plan[i + 1][1];
            int gap = nextStart - start;
            
            if (duration > gap) {
                stack.push(new int[]{id, duration - gap});
            } else {
                endList.add(id);
                
                int freeTime = gap - duration;
                
                while (!stack.isEmpty() && freeTime > 0) {
                    int[] top = stack.pop();
                    
                    if (top[1] <= freeTime) {
                        freeTime -= top[1];
                        endList.add(top[0]);
                    } else {
                        stack.push(new int[]{top[0], top[1] - freeTime});
                        freeTime = 0;
                    }
                }
            }
        }
        
        endList.add(plan[n - 1][0]);
        
        while (!stack.isEmpty()) {
            endList.add(stack.pop()[0]);
        }
        
        for (int i = 0; i < n; i++) {
            answer[i] = map.get(endList.get(i));
        }
        
        return answer;
    }
    
    public int toMin(String str){
        String[] arr = str.split(":");
        return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
    }
}