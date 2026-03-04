import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        long[] banNums = new long[bans.length];
        for (int i = 0; i < bans.length; i++) {
            banNums[i] = strToNum(bans[i]);
        }

        Arrays.sort(banNums);

        long rank = n;

        // n보다 작거나 같은 금지어가 있으면 rank 증가
        for (long ban : banNums) {
            if (ban <= rank) {
                rank++;
            } else {
                break;
            }
        }
        
        return numToStr(rank);
    }

    // 문자열 -> 숫자
    private long strToNum(String str) {
        long result = 0;
        for (int i = 0; i < str.length(); i++) {
            result = result * 26 + (str.charAt(i) - 'a' + 1);
        }
        return result;
    }

    // 숫자 -> 문자열
    private String numToStr(long num) {
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            num--;
            sb.append((char) ('a' + (num % 26)));
            num /= 26;
        }

        return sb.reverse().toString();
    }
}