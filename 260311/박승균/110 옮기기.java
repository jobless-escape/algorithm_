class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for(int i = 0; i < s.length; i++){

            String str = s[i];
            StringBuilder sb = new StringBuilder();
            int count = 0;

            for(char c : str.toCharArray()){
                sb.append(c);

                if(sb.length() >= 3){
                    int len = sb.length();

                    if(sb.charAt(len-3) == '1' &&
                       sb.charAt(len-2) == '1' &&
                       sb.charAt(len-1) == '0'){
                        sb.delete(len-3, len);
                        count++;
                    }
                }
            }

            String remain = sb.toString();

            // 마지막 0 위치
            int idx = remain.lastIndexOf("0");

            StringBuilder insertBuilder = new StringBuilder();

            for(int j = 0; j < count; j++){
                insertBuilder.append("110");
            }

            String insert = insertBuilder.toString();
                
            
            // 0이 하나도 없으면 맨 앞
            if(idx == -1) answer[i] = insert + remain;
            else answer[i] = remain.substring(0, idx+1) + insert + remain.substring(idx+1);
        }

        return answer;
    }
}