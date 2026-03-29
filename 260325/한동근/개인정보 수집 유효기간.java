import java.util.*;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        ArrayList<Integer> list = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        String[] todayArr = today.split("\\.");
        int todayYear = Integer.parseInt(todayArr[0]);
        int todayMonth = Integer.parseInt(todayArr[1]);
        int todayDay = Integer.parseInt(todayArr[2]);
        
        for(String term : terms){
            String[] arr = term.split(" ");
            map.put(arr[0], Integer.parseInt(arr[1]));
        }
        
        for(int i = 0 ; i < privacies.length ; i++){
            String[] privacyArr = privacies[i].split(" ");
            String[] privacyDate = privacyArr[0].split("\\.");
            String type = privacyArr[1];
            
            int privacyYear = Integer.parseInt(privacyDate[0]);
            int privacyMonth = Integer.parseInt(privacyDate[1]);
            int privacyDay = Integer.parseInt(privacyDate[2]);
            
            privacyMonth += map.get(type);
            
            if(privacyMonth > 12){
                //2022년 12월 + 12개월 → 2024년 0월은 존재하지 않는 날짜임. 결과는 2023년 12월
                //Month가 24, 36이면 Month 더해줄 때 0이 더해짐.
                //그래서 -1한 값으로 나머지를 구하고, +1 해주면 된다.
                privacyYear += (privacyMonth - 1) / 12;
                privacyMonth = (privacyMonth -  1) % 12 + 1;
            }
            
            privacyDay--;
            
            if(privacyDay == 0){
                privacyDay = 28;
                privacyMonth--;
                if(privacyMonth == 0){
                    privacyMonth = 12;
                    privacyYear--;
                }
            }
            if(todayYear > privacyYear){
                list.add(i + 1);
                continue;
            }
            else if(todayYear == privacyYear){
                if(todayMonth > privacyMonth){
                    list.add(i + 1);
                    continue;
                }
                else if(todayMonth == privacyMonth){
                    if(todayDay > privacyDay){
                        list.add(i + 1);
                        continue;
                    }
                }
            }
            
        }
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}