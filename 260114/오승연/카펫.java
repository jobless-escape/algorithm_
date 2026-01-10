class Solution {
    static int[] answer;
    static int garo, sero;
    public int[] solution(int brown, int yellow) {
        answer = new int[2];
        garo=yellow;
        sero=0;
        wanjeon(brown, yellow);
        return answer;
    }
    static void wanjeon(int brown, int yellow){
        sero++;
        if(yellow%sero==0){
            garo=yellow/sero;
            if((garo+2)*2+(sero+2)*2-4==brown){
                answer[0]=garo+2;
                answer[1]=sero+2;
                return;
            }else{
                wanjeon(brown,yellow);
            }
        }else{
            wanjeon(brown,yellow);
        }
    }
}
