import java.util.*;

class Solution {

    static long toNumber(String s) {
        long result = 0;
        for (char c : s.toCharArray()) {
            result = result * 26 + (c - 'a' + 1);
        }
        return result;
    }

    static String toString(long n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            sb.append((char)('a' + n % 26));
            n /= 26;
        }
        return sb.reverse().toString();
    }

    public String solution(long n, String[] bans) {
        // ban들을 숫자로 변환 후 정렬
        long[] banNums = new long[bans.length];
        for (int i = 0; i < bans.length; i++) {
            banNums[i] = toNumber(bans[i]);
        }
        Arrays.sort(banNums);

        long target = n;
        for (long ban : banNums) {
            if (ban <= target) {
                target++;  // ban된 것만큼 뒤로 밀기
            } else {
                break;     // 정렬되어 있으므로 이후는 볼 필요 없음
            }
        }

        return toString(target);
    }
}