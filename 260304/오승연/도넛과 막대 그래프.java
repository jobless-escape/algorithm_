import java.io.*;
import java.util.*;

class Solution {
    static int n;
    static List<Integer>[] fanin;
    static List<Integer>[] fanout;
    static int startnode;
    static int[] visitcnt;
    static List<Integer> visitnode;
    static int donut, stick, eight;
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        n=0;
        for(int i=0;i<edges.length;i++){
            for(int j=0;j<2;j++){
                n=Math.max(n,edges[i][j]);
            }
        }
        
        fanin=new ArrayList[n+1];
        fanout=new ArrayList[n+1];
        for(int i=0;i<n+1;i++){
            fanout[i]=new ArrayList<>();
            fanin[i]=new ArrayList<>();
        }
        
        for(int i=0;i<edges.length;i++){
            int from=edges[i][0];
            int to=edges[i][1];
            fanout[from].add(to);
            fanin[to].add(from);
        }
        
        startnode=0;
        for(int i=1;i<n+1;i++){
            if(fanout[i].size()>=2 && fanin[i].size()==0){
                startnode=i;
                break;
            }
        }
        
        donut=0;
        stick=0;
        eight=0;
        
        for(int i:fanout[startnode]){
            bfs(i);
        }
        
        answer[0]=startnode;
        answer[1]=donut;
        answer[2]=stick;
        answer[3]=eight;
        return answer;
    }
    
    static void bfs(int start){
    boolean[] visited = new boolean[n + 1];  // 수정
    visitnode = new ArrayList<>();

    Queue<Integer> q = new LinkedList<>();
    q.offer(start);
    visited[start] = true;                   // 넣을 때 방문 처리
    int startcnt = 0;
    visitnode.add(start);

    while(!q.isEmpty()){
        int now = q.poll();

        if(now == start){
            startcnt++;
        }
        if(startcnt == 2) break;

        for(int i : fanout[now]){
            if(!visited[i]){                 // 아직 방문 안 했을 때만
                visited[i] = true;           // 넣자마자 방문 표시
                q.offer(i);
                visitnode.add(i);
            }
        }
    }

    if(fanout[visitnode.get(visitnode.size() - 1)].size() == 0){
        stick++;
    } else {
        boolean flag = true;
        for(int i : visitnode){
            if(fanout[i].size() != 1) flag = false;
        }
        if(flag) donut++;

        boolean flag2 = false;
        for(int i : visitnode){
            if(fanout[i].size() == 2){
                flag2 = true;
                break;
            }
        }
        if(flag2) eight++;
    }
}

}
