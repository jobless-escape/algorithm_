import java.util.*;

class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};
        List<String> list = new ArrayList<>();
        
        HashMap<String, String> map = new HashMap<>();
        for(String st : record){
            String[] tmp = st.split(" ");
            
            if(!tmp[0].equals("Leave")){
                map.put(tmp[1], tmp[2]);
                continue;
            }
        } // 닉네임 저장
        for(String st : record){
            String[] tmp = st.split(" ");
            switch(tmp[0]){
                case "Enter":
                list.add(map.get(tmp[1]) + "님이 들어왔습니다.");
                break;
                case "Leave":
                list.add(map.get(tmp[1]) + "님이 나갔습니다.");
                break;
            }
        } // 저장한 닉네임으로 String 만들기
        
        answer = list.toArray(new String[list.size()]);
        return answer;
    }
}