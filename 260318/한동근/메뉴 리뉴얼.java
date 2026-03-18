import java.util.*;

class Solution {
    static HashMap<String, Integer> hm;
    public String[] solution(String[] orders, int[] course) {
        ArrayList<String> answer = new ArrayList<>();
        
        for(int count : course){
            hm = new HashMap<>();
            
            for(String order : orders){
                char[] arr = order.toCharArray();
                Arrays.sort(arr);
                combo(arr, "", 0, count);
            }
            
            ArrayList<Integer> list = new ArrayList<>(hm.values());
            ArrayList<String> keySet = new ArrayList<>(hm.keySet());
            int max = 0;
            
            for(int c : list) max = Math.max(max, c);
            
            if(max >= 2){
                for(String key : keySet){
                    int value = hm.get(key);
                
                    if(value == max) answer.add(key);
                }
            }
        }
        
        Collections.sort(answer);
        
        String[] ans = new String[answer.size()];
        
        for(int i = 0 ; i < answer.size() ; i++){
            ans[i] = answer.get(i);
        }
        return ans;
    }
    
    static void combo(char[] arr, String text, int now, int count){
        if(text.length() == count){
            hm.put(text, hm.getOrDefault(text, 0) + 1);
            return;
        }
        
        for(int i = now ; i < arr.length ; i++){
            combo(arr, text + arr[i], i + 1, count);
        }
    }
}