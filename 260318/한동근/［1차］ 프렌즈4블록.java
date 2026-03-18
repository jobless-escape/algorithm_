class Solution {
    static char[][] map;
    static int answer;
    
    public int solution(int m, int n, String[] board) {
        map = new char[m][n];
        answer = 0;
        
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                map[i][j] = board[i].charAt(j);
            }
        }
        
        while(true){
            boolean[][] remove = new boolean[m][n];
            int removeCount = 0;
            for(int i = 0 ; i < m - 1 ; i++){
                for(int j = 0 ; j < n - 1 ; j++){
                    char a = map[i][j];
                    char b = map[i+1][j];
                    char c = map[i][j+1];
                    char d = map[i+1][j+1];
                    
                    if(a != ' ' && a == b && a == c && a == d){
                        remove[i][j] = true;
                        remove[i+1][j] = true;
                        remove[i][j+1] = true;
                        remove[i+1][j+1] = true;
                    }
                }
            }
            
            for(int i = 0 ; i < m ; i++){
                for(int j = 0 ; j < n ;j++){
                    if(remove[i][j]){
                        removeCount++;
                        map[i][j] = ' ';
                    }
                }
            }
            
            if(removeCount == 0) break;
            
            for(int i = m - 2 ; i >= 0 ; i--){
                for(int j = n - 1 ; j >= 0 ; j--){
                    if(map[i][j] != ' ' && map[i+1][j] == ' '){
                        int k = i;
                        while(k + 1 < m){
                            if(map[k + 1][j] != ' ') break;
                            k++;
                        }
                        map[k][j] = map[i][j];
                        map[i][j] = ' ';
                    }
                }
            }
            answer += removeCount;
        }
        return answer;
    }
}