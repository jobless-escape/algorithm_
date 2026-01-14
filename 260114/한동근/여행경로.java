import java.util.*;

class Solution {
    static ArrayList<String> allRoutes;
    static boolean[] visited;
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        allRoutes = new ArrayList<>();
        visited = new boolean[tickets.length];
        dfs("ICN", "ICN", tickets, 0);
        Collections.sort(allRoutes);
        answer = allRoutes.get(0).split(" ");
        return answer;
    }
    
    static void dfs(String from, String route, String[][] ticket, int count){
        if(count == ticket.length){
            allRoutes.add(route);
            return;
        }
        
        for(int i = 0 ; i < ticket.length ; i++){
            if(!visited[i] && from.equals(ticket[i][0])){
                visited[i] = true;
                dfs(ticket[i][1], route + " " + ticket[i][1], ticket, count + 1);
                visited[i] = false;
            }
        }
    }
}