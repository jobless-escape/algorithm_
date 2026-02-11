class Solution {
    public String solution(String s) {
        StringBuilder sb = new StringBuilder();

        s = s.toLowerCase();

        boolean newWord = true;
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);

            if(c ==' '){
                sb.append(c);
                newWord = true;
            }else if(newWord){
                sb.append(Character.toUpperCase(c));
                newWord = false;
            }else{
                sb.append(c);
            }
        }
        return sb.toString();
    }
}