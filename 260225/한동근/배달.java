import java.util.*;

class Solution {
    static class Node{
        int to, cost;

        public Node(int to, int cost){
            this.to = to;
            this.cost = cost;
        }
    }

    public int solution(int N, int[][] road, int K) {
        ArrayList<Node>[] graph = new ArrayList[N + 1];
        for(int i = 1 ; i <= N ; i++){
            graph[i] = (new ArrayList<>());
        }

        int answer = 0;
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        pq.offer(new Node(1, 0));
        dist[1] = 0;

        for(int[] r : road){
            int from = r[0];
            int to = r[1];
            int cost = r[2];

            graph[from].add(new Node(to, cost));
            graph[to].add(new Node(from, cost));
        }

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(now.cost > dist[now.to]) continue;

            for(Node next : graph[now.to]){
                int newCost = now.cost + next.cost;
                if(newCost < dist[next.to]){
                    dist[next.to] = newCost;
                    pq.offer(new Node(next.to, newCost));
                }
            }
        }

        for(int val : dist) if(val <= K) answer++;
        return answer;
    }
}