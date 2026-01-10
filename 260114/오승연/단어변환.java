import java.io.*;
import java.util.*;

class Solution {
    static String be;
    static int len;
    static boolean flag;
    static boolean[] visited;
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        be=begin;
        len=begin.length();
        flag=false;
        visited=new boolean[words.length];
        
        while(true){
            if(be.equals(target)){
                break;
            }
            if(flag){ // flag 변경되면 = 변환못한다는 소리
                answer=0;
                break;
            }
            dfs(target, words);
            answer++;
        }
        return answer;
    }
    static void dfs(String target, String[] words){
    String[] be_arr = be.split("");
    ArrayList<Integer> list = new ArrayList<>();

    for(int j=0;j<words.length;j++){
        if(visited[j]) continue;

        String[] word_arr=words[j].split("");
        int count=0;

        for(int i=0;i<len;i++){
            if(!be_arr[i].equals(word_arr[i])) count++;
        }

        if(count==1) list.add(j);
    }

    // target과 같은거 있는지 검사
    for(int idx : list){
        if(words[idx].equals(target)){
            be = words[idx]; 
            visited[idx]=true;
            return;
        }
    }

    // target과 같은게 안나오면 be 업데이트하고 다시 돌린다.
    if(list.size()>0){
        int idx=list.get(0);
        be=words[idx];
        visited[idx]=true;
        return;
    }

    // 한 개의 알파벳으로 못바꿀때 flag 변경.
    flag=true;
}


}
