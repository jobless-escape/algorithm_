import java.io.*;
import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {        
        Map<String,Integer> park=new HashMap<>(); //차번호,들어온시간
        Map<String,Integer> totalfee=new TreeMap<>(); //차번호,주차비
        
        for(String record:records){
            String[] arr=record.split(" ");
            int time=getTime(arr[0]);
            String car=arr[1];
            String inout=arr[2];
            
            if(inout.equals("IN")){
                park.put(car,time);
            }else{
                if(totalfee.containsKey(car)){
                    totalfee.put(car,totalfee.get(car)+time-park.get(car));
                }else{
                    totalfee.put(car,time-park.get(car));
                }
                park.remove(car);
            }
        }
        
        if(!park.isEmpty()){
            for(String c:park.keySet()){
                int fee=0;
                if(totalfee.get(c)!=null){
                    fee=totalfee.get(c);
                }else{
                    fee=0;
                }
                totalfee.put(c,fee+(23*60+59)-park.get(c));
            }
        }
        
        List<Integer> answer=new ArrayList<>();
        for(Integer t:totalfee.values()){
            int basictime=fees[0];
            int basicfee=fees[1];
            int parttime=fees[2];
            int partfee=fees[3];
            
            if(t<=basictime){
                answer.add(basicfee);
            }else{
                answer.add(basicfee+(int) Math.ceil((double)(t - basictime) / parttime)*partfee);
            }
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    static int getTime(String time){
        String[] t=time.split(":");
        return (Integer.parseInt(t[0])*60+Integer.parseInt(t[1]));
    }
}
