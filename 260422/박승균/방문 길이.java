import java.util.*;

class Solution {
    
    int[][] dxdy = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}}; //x,y
    
    public int solution(String dirs) {
        int answer = 0;
        
        int x = 0; int y = 0;
        Set<String> set = new HashSet<>();
        for(int i = 0; i < dirs.length(); i++){
            char ch = dirs.charAt(i);
            
            int[] location = move(x, y, ch);
            if(!isEdge(location[0], location[1])){
                set.add(normalize(x, y, location[0], location[1]));

                x = location[0]; y = location[1];
            }
        }
        
        return set.size();
    }
    
    public int[] move(int x, int y, char ch){
        int[] location = {};
        switch(ch){
            case 'U':
                location = new int[]{x + dxdy[0][0], y + dxdy[0][1]};
                break;
            case 'D':
                location = new int[]{x + dxdy[1][0], y + dxdy[1][1]};
                break;
            case 'L':
                location = new int[]{x + dxdy[2][0], y + dxdy[2][1]};
                break;
            case 'R':
                location = new int[]{x + dxdy[3][0], y + dxdy[3][1]};
                break;
        }
        return location;
    }
    
    public String normalize(int x1, int y1, int x2, int y2){
        // 0,0 -> 1,0   / 1,0 -> 0,0 같은거
        // 1,3 -> 1,4  // 1,4 -> 1,3
        if(x1 > x2 || y1 > y2) return x2 + "," + y2 + "," + x1 + "," + y1;
        else return x1 + "," + y1 + "," + x2 + "," + y2;
    }
    
    public boolean isEdge(int x, int y){
        return x < -5 || x > 5 || y < -5 || y > 5;
    }
}