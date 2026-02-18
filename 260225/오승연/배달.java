import java.util.*;
import java.io.*;

class Solution {
    class Node{
        int vertex;
        int weight;
        
        public Node(int vertex, int weight){
            this.vertex=vertex;
            this.weight=weight;
        }
    }
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        List<List<Node>> list=new ArrayList<>();
        for(int i=0;i<N+1;i++){
            list.add(new ArrayList<>());
        }
        
        for(int i=0;i<road.length;i++){
            int u=road[i][0];
            int v=road[i][1];
            int w=road[i][2];
            
            list.get(u).add(new Node(v,w));
            list.get(v).add(new Node(u,w));
        }
        
        PriorityQueue<Node> pq=new PriorityQueue<>(
            (a,b)->Integer.compare(a.weight,b.weight)
        );
        pq.add(new Node(1,0));
        
        int[] dist=new int[N+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[1]=0;
        
        while(!pq.isEmpty()){
            Node current=pq.poll();
            
            // if (current.weight > dist[current.vertex]) continue;
            
            for(Node next:list.get(current.vertex)){
                if(next.weight+dist[current.vertex]<dist[next.vertex]){
                    dist[next.vertex]=next.weight+dist[current.vertex];
                    pq.add(new Node(next.vertex,dist[next.vertex]));
                }
            }
        }
        
        for(int i=1;i<N+1;i++){
            if(dist[i]<=K) answer++;
        }

        return answer;
    }
}
