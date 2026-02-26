import java.util.*;
class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        List<List<int[]>> adj = new ArrayList<>();
        for(int i = 0; i <= N; i++) adj.add(new ArrayList<>()); //1-based
        
        for(int[] r: road){
            int from = r[0]; int to = r[1]; int cost = r[2];
            adj.get(from).add(new int[]{to, cost});
            adj.get(to).add(new int[]{from, cost});
        }
        
        PriorityQueue<int[]> q = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        q.add(new int[]{1, 0}); // 현재노드, 비용
        dist[1] = 0;
        
        while(!q.isEmpty()){
            int[] cur = q.poll();
            int cn = cur[0]; int cc = cur[1];
            if(cc > dist[cn]) continue;
            // 인접 리스트 찾아서 더한 비용이 현재 최소 비용보다 작으면 갱신
            for(int[] near: adj.get(cn)){
                int nn = near[0]; int nc = near[1];
                if(dist[nn] > cc + nc){
                    dist[nn] = cc + nc;
                    q.add(new int[]{nn, dist[nn]});
                }
            }
            
        }
        
        for(int i = 1; i <= N; i++) if(dist[i] <= K) answer++;
        return answer;
    }
}