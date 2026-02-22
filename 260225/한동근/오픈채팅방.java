import java.util.*;

class Solution {
    static String enterMsg = "님이 들어왔습니다.";
    static String leaveMsg = "님이 나갔습니다.";
    
    static class Log{
        String uid;
        String op;
        
        public Log(String uid, String op){
            this.uid = uid;
            this.op = op;
        }
        
    }
    public String[] solution(String[] record) {
        ArrayList<Log> logs = new ArrayList<>();
        HashMap<String, String> users = new HashMap<>();
        
        for(String info : record){
            String[] infoArr = info.split(" ");
            
            String op = infoArr[0];
            String uid = infoArr[1];
            
            if(op.equals("Enter")){
                String name = infoArr[2];
                logs.add(new Log(uid, op));
                users.put(uid, name);
            }
            else if(op.equals("Leave")){
                logs.add(new Log(uid, op));
            }
            else if(op.equals("Change")){
                String name = infoArr[2];
                users.put(uid, name);
            }
        }
        String[] answer = new String[logs.size()];
            
        for(int i = 0 ; i < logs.size() ; i++){
            Log log = logs.get(i);
                
            if(log.op.equals("Enter")) answer[i] = users.get(log.uid) + enterMsg;
            else answer[i] = users.get(log.uid) + leaveMsg;
        }
        return answer;
    }
}