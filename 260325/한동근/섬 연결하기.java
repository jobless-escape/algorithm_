import java.util.*;

class Solution {
    static int[] parent;
    public int solution(int n, int[][] costs) {
        int answer = 0;
        parent = new int[n];
        for(int i = 0 ; i < n ; i++) parent[i] = i;
        
        Arrays.sort(costs, (a, b) -> a[2] - b[2]);
        
        for(int i = 0 ; i < costs.length ; i++){
            if(find(costs[i][0]) != find(costs[i][1])){
                union(costs[i][0], costs[i][1]);
                answer += costs[i][2];
            }
        }
        return answer;
    }
    
    static int find(int a){
        if(parent[a] == a) return a;
        else return parent[a] = find(parent[a]);
    }
    
    static void union(int a, int b){
        int root1 = find(a);
        int root2 = find(b);
        
        if(root1 != root2){
            parent[root2] = root1;
        }
    }
}